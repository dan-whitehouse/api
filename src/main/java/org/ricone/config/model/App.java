package org.ricone.config.model;

import org.apache.commons.collections.CollectionUtils;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.School;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    private String id;
    private String severityLevelDataAPI;
    private String providerSecret;
    private String profile_id;

    private List<District> districts = new ArrayList<>();
    private List<Lea> leas = new ArrayList<>();
    private List<PathPermission> permissions = new ArrayList<>();

    public App() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeverityLevelDataAPI() {
        return severityLevelDataAPI;
    }

    public void setSeverityLevelDataAPI(String severityLevelDataAPI) {
        this.severityLevelDataAPI = severityLevelDataAPI;
    }

    public String getProviderSecret() {
        return providerSecret;
    }

    public void setProviderSecret(String providerSecret) {
        this.providerSecret = providerSecret;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Lea> getLeas() {
        return leas;
    }

    public void setLeas(List<Lea> leas) {
        this.leas = leas;
    }

    public List<PathPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PathPermission> permissions) {
        this.permissions = permissions;
    }

    /* Custom Methods */
    public List<String> getDistrictLocalIds() {
        return districts.stream().map(District::getId).collect(Collectors.toList());
    }

    public Lea getLea(String refId) {
        if(CollectionUtils.isNotEmpty(leas)) {
            Optional<Lea> instance = leas.stream().filter(lea -> lea.getLeaRefId().equalsIgnoreCase(refId)).findFirst();
            if(instance.isPresent()) {
                return instance.get();
            }
        }
        return null;
    }

    public School getSchool(String refId) {
        if(CollectionUtils.isNotEmpty(leas)) {
            for (Lea lea : leas) {
                if(CollectionUtils.isNotEmpty(lea.getSchools())) {
                    Optional<School> instance = lea.getSchools().stream().filter(school -> school.getSchoolRefId().equalsIgnoreCase(refId)).findFirst();
                    if(instance.isPresent()) {
                        return instance.get();
                    }
                }
            }
        }
        return null;
    }

    public District getDistrictByLea(String refId) {
        if(CollectionUtils.isNotEmpty(leas)) {
            Optional<Lea> lea = leas.stream().filter(l -> l.getLeaRefId().equalsIgnoreCase(refId)).findFirst();
            if(lea.isPresent()) {
                return districts.stream().filter(d -> d.getId().equalsIgnoreCase(lea.get().getLeaId())).findFirst().get();
            }
        }
        return null;
    }

    public District getDistrictBySchool(String refId) {
        if(CollectionUtils.isNotEmpty(leas)) {
            Optional<Lea> lea = leas.stream().filter(l -> l.getLeaRefId().equalsIgnoreCase(refId)).findFirst();
            if(lea.isPresent() && CollectionUtils.isNotEmpty(lea.get().getSchools())) {
                Optional<School> school = lea.get().getSchools().stream().filter(s -> s.getSchoolRefId().equalsIgnoreCase(refId)).findFirst();
                if(school.isPresent()) {
                    return districts.stream().filter(d -> d.getId().equalsIgnoreCase(lea.get().getLeaId())).findFirst().get();
                }
            }
        }
        return null;
    }

    public HashMap<String, String> getDistrictKVsByLea(String refId) {
        for (District district : districts) {
            if(CollectionUtils.isNotEmpty(leas)) {
                Optional<Lea> oLea = leas.stream().filter(lea -> lea.getLeaId().equalsIgnoreCase(district.getId())).findFirst();
                if(oLea.isPresent()) {
                    return district.getKv();
                }
            }
        }
        return null;
    }

    public HashMap<String, String> getDistrictKVsBySchool(String refId) {
        for (District district : districts) {
            if(CollectionUtils.isNotEmpty(leas)) {
                Optional<Lea> oLea = leas.stream().filter(lea -> lea.getLeaId().equalsIgnoreCase(district.getId())).findFirst();
                if(oLea.isPresent()) {
                    if(CollectionUtils.isNotEmpty(oLea.get().getSchools())) {
                        Optional<School> oSchool = oLea.get().getSchools().stream().filter(school -> school.getSchoolRefId().equalsIgnoreCase(refId)).findFirst();
                        if(oSchool.isPresent()) {
                            return district.getKv();
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "App{" + "id='" + id + '\'' + ", severityLevelDataAPI='" + severityLevelDataAPI + '\'' + ", providerSecret='" + providerSecret + '\'' + ", profile_id='" + profile_id + '\'' + ", districts=" + districts + '}';
    }
}
