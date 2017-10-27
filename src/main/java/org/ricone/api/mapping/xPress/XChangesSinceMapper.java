package org.ricone.api.mapping.xPress;

import org.ricone.api.model.core.EventLog;
import org.ricone.api.model.xpress.XChangeSince;
import org.ricone.api.model.xpress.XChangesSince;
import org.ricone.api.model.xpress.XChangesSinceResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XChangesSinceMapper")
public class XChangesSinceMapper {

    public XChangesSinceResponse convert(List<EventLog> instance) {
        List<XChangeSince> list = new ArrayList<>();
        for(EventLog eventLog : instance)
        {
            XChangeSince xChangeSince = map(eventLog);
            if (xChangeSince != null) {
                list.add(xChangeSince);
            }
        }

        XChangesSinceResponse response = new XChangesSinceResponse();
        XChangesSince xChangesSince = new XChangesSince();
        xChangesSince.setXChangeSince(list);

        response.setXChangesSince(xChangesSince);
        return response;
    }

    private XChangeSince map(EventLog eventLog) {

        XChangeSince xChangeSince = new XChangeSince();
        xChangeSince.setRefId(eventLog.getObjectRefId());
        xChangeSince.setEventType(eventLog.getEventType());
        xChangeSince.setEventDate(eventLog.getEventTimestamp().toString());

        return xChangeSince;
    }
}
