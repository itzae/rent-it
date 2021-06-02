package com.itgonca.rentit.utils.functional

class FirebaseFailure : Failure.CustomFailure() {
    object EmailExist : Failure.CustomFailure()
}