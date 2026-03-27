package org.example

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.elemMatch
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Filters.lt
import org.bson.Document
import javax.management.Query.eq
import javax.management.Query.lt

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var uri = "mongodb+srv://static_void:ezequieleric@cluster0.ygafmhj.mongodb.net/?appName=Cluster0"

    var mongo: MongoClient = MongoClients.create(uri)

    var coll: MongoCollection<Document>? = null

    try {
        var database = mongo.getDatabase("sample_training")
        coll = database.getCollection("grades")
    } catch (e: Exception) {
        println("ERROR: ${e.message}")
    }

    //exercici1(coll)

    //Exercici 2

    //Filtre1
    println()
    println("Filtre1")
    var filter = eq("group", "1");
    var cursor = coll!!.find(filter).iterator()
    while (cursor.hasNext()) {
        println(cursor.next().toJson())
    }

    //Filtre2
    println()
    println("Filtre2")
    var subfiltre = eq("score", 100)
    filter = elemMatch("scores", subfiltre)
    cursor = coll!!.find(filter).iterator()
    while (cursor.hasNext()) {
        println(cursor.next().toJson())
    }


    //Filtre3
    println()
    println("Filtre3")
    subfiltre = and(eq("type", "exam"), lt("score", 50))
    filter = elemMatch("scores", subfiltre)
    cursor = coll!!.find(filter).iterator()
    while (cursor.hasNext()) {
        println(cursor.next().toJson())
    }
}


fun exercici1(coll: MongoCollection<Document>?) {
    var llistatStudents = mutableListOf<Document>()

    var docStudent1 = Document.parse(
        """
            {
               "student_id":111333444,
               "name":"Ezequiel",
               "surname":"Carracedo",
               "class_id":"DAM",
               "group":"1",
               "scores":[
                  {
                     "type":"exam",
                     "score":100
                  },
                  {
                     "type":"teamWork",
                     "score":50
                  }
               ]
            }
        """.trimIndent()
    )

    var docStudent2 = Document.parse(
        """
            {
               "student_id":111222333,
               "name":"Èric",
               "surname":"Moral",
               "class_id":"DAM",
               "group":"1",
               "interests":[
                  "music",
                  "gym",
                  "code",
                  "electronics"
               ]
            }
        """.trimIndent()
    )

    llistatStudents.add(docStudent1)
    llistatStudents.add(docStudent2)

    coll!!.insertMany(llistatStudents)
}