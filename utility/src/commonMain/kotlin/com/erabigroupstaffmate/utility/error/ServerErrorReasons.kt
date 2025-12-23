package com.erabigroupstaffmate.utility.error

enum class ServerErrorReasons : ErrorReason {

    NoNetwork,

    UnknownError,

    EmailInUse,

    WrongPass,


    TooManyReq,

    InvalidEmail,

    WeakPassword,

    EmailReq,

    Canceled,

    Timeout,

    InvalidArgs,

    PermissionDenied,

    Unavailable,

    NotFound,


    BadCertificate,

    BadResponse,

    BadRequest,


    UnAuthorised,

    Forbidden,

    Conflict,

    InternalServerError,

    NoChainFound,

    NoBranchForChainFound,

}