package org.ricone.api.engine;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import org.ricone.api.model.core.LeaTelephone;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeatelephone is a Querydsl query type for LeaTelephone
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLeatelephone extends EntityPathBase<LeaTelephone> {

    private static final long serialVersionUID = 140431135L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeatelephone leatelephone = new QLeatelephone("leatelephone");

    public final QLea lea;

    public final StringPath leatelephoneRefId = createString("leatelephoneRefId");

    public final BooleanPath primaryTelephoneNumberIndicator = createBoolean("primaryTelephoneNumberIndicator");

    public final StringPath telephoneNumber = createString("telephoneNumber");

    public final StringPath telephoneNumberTypeCode = createString("telephoneNumberTypeCode");

    public QLeatelephone(String variable) {
        this(LeaTelephone.class, forVariable(variable), INITS);
    }

    public QLeatelephone(Path<? extends LeaTelephone> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLeatelephone(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLeatelephone(PathMetadata metadata, PathInits inits) {
        this(LeaTelephone.class, metadata, inits);
    }

    public QLeatelephone(Class<? extends LeaTelephone> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lea = inits.isInitialized("lea") ? new QLea(forProperty("lea")) : null;
    }

}

