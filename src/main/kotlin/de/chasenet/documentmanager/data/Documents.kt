package de.chasenet.documentmanager.data

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date
import java.util.*

object Documents : UUIDTable() {
    val name = text("document_name").uniqueIndex()
    val path = text("document_path").uniqueIndex()
    val date = date("document_date")
}

class Document(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Document>(Documents)

    var name by Documents.name
    var path by Documents.path
    var date by Documents.date
    var tags by Tag via DocumentTags
}
