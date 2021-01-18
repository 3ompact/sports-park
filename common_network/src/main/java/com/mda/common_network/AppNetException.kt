package com.mda.common_network

class AppNetException : Exception {
    var errCode: Int = 0
    var errMsg: String? = null
    var exMsg : String? = null

    constructor(code: Int, msg: String?) : super(msg) {
        this.errMsg = msg
        this.errCode = code
    }

    constructor(error: Error, e: Throwable?) {
        this.errCode = error.getCodeKey()
        this.errMsg = error.getValue()
        this.exMsg = e?.message
    }



}