package ci.orange.domain.model

data class DefaultResponse(
    val hasError:Boolean,
    val status: Status
)

data class Status(
    val code:String,
    val message:String
)
