package top.fuadreza.tukaruang.core.enums.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import top.fuadreza.tukaruang.core.enums.Currency

object CurrencyRateMapSerializer : KSerializer<Map<Currency, Double>> {
  override val descriptor: SerialDescriptor =
    MapSerializer(String.serializer(), Double.serializer()).descriptor

  override fun serialize(encoder: Encoder, value: Map<Currency, Double>) {
    val stringMap = value.mapKeys { it.key.name } // convert enum to string
    encoder.encodeSerializableValue(MapSerializer(String.serializer(), Double.serializer()), stringMap)
  }

  override fun deserialize(decoder: Decoder): Map<Currency, Double> {
    val stringMap = decoder.decodeSerializableValue(MapSerializer(String.serializer(), Double.serializer()))
    return stringMap.mapNotNull { (key, value) ->
      try {
        Currency.valueOf(key) to value
      } catch (e: IllegalArgumentException) {
        null // skip unknown currency keys
      }
    }.toMap()
  }
}