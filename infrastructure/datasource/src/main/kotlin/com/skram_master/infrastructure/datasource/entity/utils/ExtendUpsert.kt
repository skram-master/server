package com.skram_master.infrastructure.datasource.entity.utils

import com.skram_master.infrastructure.datasource.entity.TimestampTable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.batchUpsert
import org.jetbrains.exposed.sql.statements.BatchUpsertStatement
import org.jetbrains.exposed.sql.statements.ReturningStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.jetbrains.exposed.sql.statements.UpsertBuilder
import org.jetbrains.exposed.sql.statements.UpsertStatement
import org.jetbrains.exposed.sql.upsert
import org.jetbrains.exposed.sql.upsertReturning

private fun <T : TimestampTable<*>> T.internalOnUpdate(
    onUpdate: (UpsertBuilder.(UpdateStatement) -> Unit)? = null,
): (UpsertBuilder.(UpdateStatement) -> Unit) {
    val table = this
    return { statement ->
        statement[table.updatedAt] = Clock.System.now().toLocalDateTime(TimeZone.UTC)
        onUpdate?.invoke(this, statement)
    }
}

internal fun <T : TimestampTable<*>> T.upsertWithUpdatedAt(
    vararg keys: Column<*>,
    onUpdate: (UpsertBuilder.(UpdateStatement) -> Unit)? = null,
    onUpdateExclude: List<Column<*>>? = null,
    where: (SqlExpressionBuilder.() -> Op<Boolean>)? = null,
    body: T.(UpsertStatement<Long>) -> Unit,
): UpsertStatement<Long> {
    return upsert(
        *keys,
        onUpdate = internalOnUpdate<T>(onUpdate = onUpdate),
        onUpdateExclude = onUpdateExclude,
        where = where,
        body = body,
    )
}

@Suppress("LongParameterList")
internal fun <T : TimestampTable<*>> T.upsertReturningWithUpdatedAt(
    vararg keys: Column<*>,
    returning: List<Expression<*>> = columns,
    onUpdate: (UpsertBuilder.(UpdateStatement) -> Unit)? = null,
    onUpdateExclude: List<Column<*>>? = null,
    where: (SqlExpressionBuilder.() -> Op<Boolean>)? = null,
    body: T.(UpsertStatement<Long>) -> Unit,
): ReturningStatement {
    return upsertReturning(
        *keys,
        returning = returning,
        onUpdate = internalOnUpdate<T>(onUpdate = onUpdate),
        onUpdateExclude = onUpdateExclude,
        where = where,
        body = body,
    )
}

@Suppress("LongParameterList")
internal fun <T : TimestampTable<*>, E : Any> T.batchUpsertWithUpdatedAt(
    data: Iterable<E>,
    vararg keys: Column<*>,
    onUpdate: (UpsertBuilder.(UpdateStatement) -> Unit)? = null,
    onUpdateExclude: List<Column<*>>? = null,
    where: (SqlExpressionBuilder.() -> Op<Boolean>)? = null,
    shouldReturnGeneratedValues: Boolean = true,
    body: BatchUpsertStatement.(E) -> Unit,
): List<ResultRow> {
    return batchUpsert(
        data = data,
        keys = keys,
        onUpdate = internalOnUpdate<T>(onUpdate = onUpdate),
        onUpdateExclude = onUpdateExclude,
        where = where,
        shouldReturnGeneratedValues = shouldReturnGeneratedValues,
        body = body,
    )
}

@Suppress("LongParameterList")
internal fun <T : TimestampTable<*>, E : Any> T.batchUpsertWithUpdatedAt(
    data: Sequence<E>,
    vararg keys: Column<*>,
    onUpdate: (UpsertBuilder.(UpdateStatement) -> Unit)? = null,
    onUpdateExclude: List<Column<*>>? = null,
    where: (SqlExpressionBuilder.() -> Op<Boolean>)? = null,
    shouldReturnGeneratedValues: Boolean = true,
    body: BatchUpsertStatement.(E) -> Unit,
): List<ResultRow> {
    return batchUpsert(
        data = data,
        keys = keys,
        onUpdate = internalOnUpdate<T>(onUpdate = onUpdate),
        onUpdateExclude = onUpdateExclude,
        where = where,
        shouldReturnGeneratedValues = shouldReturnGeneratedValues,
        body = body,
    )
}
