package com.skram_master.infrastructure.datasource.entity.utils

import com.skram_master.infrastructure.datasource.entity.TimestampTable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.statements.ReturningStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.updateReturning

private inline fun <T : TimestampTable<*>> T.bodyWithUpdateAt(
    crossinline body: T.(UpdateStatement) -> Unit,
): (T.(UpdateStatement) -> Unit) {
    return { statement ->
        statement[this@bodyWithUpdateAt.updatedAt] =
            Clock.System.now().toLocalDateTime(TimeZone.UTC)
        body(this@bodyWithUpdateAt, statement)
    }
}

internal inline fun <T : TimestampTable<*>> T.updateWithUpdatedAt(
    where: SqlExpressionBuilder.() -> Op<Boolean>,
    limit: Int? = null,
    crossinline body: T.(UpdateStatement) -> Unit,
): Int {
    return update(
        where = where,
        limit = limit,
        body = bodyWithUpdateAt(body),
    )
}

internal inline fun <T : TimestampTable<*>> T.updateWithUpdatedAt(
    limit: Int? = null,
    crossinline body: T.(UpdateStatement) -> Unit,
): Int {
    return update(
        limit = limit,
        body = bodyWithUpdateAt(body),
    )
}

internal inline fun <T : TimestampTable<*>> T.updateReturningWithUpdatedAt(
    returning: List<Expression<*>> = columns,
    where: SqlExpressionBuilder.() -> Op<Boolean>,
    crossinline body: T.(UpdateStatement) -> Unit,
): ReturningStatement {
    return updateReturning(
        returning = returning,
        where = where,
        body = bodyWithUpdateAt(body),
    )
}

internal inline fun <T : TimestampTable<*>> T.updateReturningWithUpdatedAt(
    returning: List<Expression<*>> = columns,
    crossinline body: T.(UpdateStatement) -> Unit,
): ReturningStatement {
    return updateReturning(
        returning = returning,
        body = bodyWithUpdateAt(body),
    )
}
