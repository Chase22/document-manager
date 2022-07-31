package de.chasenet.documentmanager.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

object Tags: IntIdTable() {
    val tag = text("tag").uniqueIndex()
}

class Tag(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Tag>(Tags)

    var tag by Tags.tag
}

object DocumentTags: Table() {
    val document = reference("document", Documents)
    val tag = reference("tag", Tags)
    override val primaryKey = PrimaryKey(document, tag)
}