package com.itgonca.rentit.utils.functional

sealed class Failure {
    object NetworkConnection : Failure()
    data class UnexpectedFailure(val message: String) : Failure()
    abstract class CustomFailure : Failure()
}