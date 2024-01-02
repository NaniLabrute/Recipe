package com.example.recipe.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
/*
object FirestoreInstance {
    private val firestoreInstance by lazy {
        FirebaseFirestore.getInstance()
    }

    // Access this instance throughout the app
    fun getFirestoreInstance(): FirebaseFirestore {
        return firestoreInstance
    }
}
*//*
object FirestoreInstance {
    private val firestoreInstance by lazy {
        Firebase.firestore
    }

    // Access this instance throughout the app
    fun getFirestoreInstance(): FirebaseFirestore {
        return firestoreInstance
    }
}*/