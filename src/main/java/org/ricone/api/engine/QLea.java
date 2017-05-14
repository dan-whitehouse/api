package org.ricone.api.engine;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import org.ricone.api.model.Lea;
import org.ricone.api.model.LeaTelephone;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLea is a Querydsl query type for Lea
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLea extends EntityPathBase<Lea> {

    private static final long serialVersionUID = 1671359109L;

    public static final QLea lea = new QLea("lea");

    public final StringPath addressCountyName = createString("addressCountyName");

    public final StringPath addressType = createString("addressType");

    public final StringPath city = createString("city");

    public final StringPath countryCode = createString("countryCode");

    public final StringPath leaid = createString("leaid");

    public final StringPath leaname = createString("leaname");

    public final StringPath leancesid = createString("leancesid");

    public final StringPath learefId = createString("learefId");

    public final StringPath leaseaid = createString("leaseaid");

    public final SetPath<LeaTelephone, QLeatelephone> leatelephones = this.<LeaTelephone, QLeatelephone>createSet("leatelephones", LeaTelephone.class, QLeatelephone.class, PathInits.DIRECT2);

    public final StringPath leavendorId = createString("leavendorId");

    public final StringPath line2 = createString("line2");

    public final StringPath postalCode = createString("postalCode");

    public final StringPath stateCode = createString("stateCode");

    public final StringPath streetNumberAndName = createString("streetNumberAndName");

    public QLea(String variable) {
        super(Lea.class, forVariable(variable));
    }

    public QLea(Path<? extends Lea> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLea(PathMetadata metadata) {
        super(Lea.class, metadata);
    }

}

