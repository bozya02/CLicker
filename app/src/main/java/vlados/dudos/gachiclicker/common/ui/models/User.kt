package vlados.dudos.gachiclicker.common.ui.models

data class User(
    var nickName: String,
    var email: String,
    var password: String,
    var currentCum: Long,
    var CPC: Int,
    var CPS: Long
)