package com.erabigroupstaffmate.parser

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

class Parser(val json: Json) {
    inline fun <reified T : Any> fromJson(
        string: String,
        deserializer: DeserializationStrategy<T>,
    ): T {
        return json.decodeFromString(deserializer, string)
    }

    inline fun <reified T : Any> requireFromJson(
        string: String,
    ): T = json.decodeFromString(string)

    inline fun <reified T : Any> fromJson(string: String?): T? {
        if (string == null) return null
        return json.decodeFromString<T>(string)
    }

    inline fun <reified T : Any> fromJson(jsonObject: JsonObject?): T? {
        if (jsonObject == null) return null
        return json.decodeFromJsonElement<T>(jsonObject)
    }

    inline fun <reified T : Any> toJson(serializer: SerializationStrategy<T>, value: T): String {
        return json.encodeToString(serializer = serializer, value = value)
    }

    inline fun <reified T : Any> toJson(value: T?): String {
        return json.encodeToString(value)
    }


    inline fun <reified T : Any> toJsonElement(value: T): JsonElement {
        return json.encodeToJsonElement(value)
    }

    @OptIn(InternalSerializationApi::class)
    fun <T : Any> fromJson(
        clazz: KClass<out T>,
        jsonString: String,
    ) = json.decodeFromString(clazz.serializer(), jsonString)

}