require "date"
require "emery"

module TestService
  class Message
    include DataClass
    val :field, Integer
  end

  class Nested
    include DataClass
    val :field, String
  end

  class Parent
    include DataClass
    val :field, String
    val :nested, Nested
  end

  class Choice
    include Enum
    define :first_choice, 'FIRST_CHOICE'
    define :second_choice, 'SECOND_CHOICE'
    define :third_choice, 'THIRD_CHOICE'
  end

  class EnumFields
    include DataClass
    val :enum_field, Choice
  end

  class NumericFields
    include DataClass
    val :int_field, Integer
    val :long_field, Integer
    val :float_field, Float
    val :double_field, Float
    val :decimal_field, Float
  end

  class NonNumericFields
    include DataClass
    val :boolean_field, Boolean
    val :string_field, String
    val :uuid_field, UUID
    val :date_field, Date
    val :datetime_field, DateTime
  end

  class ArrayFields
    include DataClass
    val :int_array_field, T.array(Integer)
    val :string_array_field, T.array(String)
  end

  class MapFields
    include DataClass
    val :int_map_field, T.hash(String, Integer)
    val :string_map_field, T.hash(String, String)
  end

  class OptionalFields
    include DataClass
    val :int_option_field, T.nilable(Integer)
    val :string_option_field, T.nilable(String)
  end

  class RawJsonField
    include DataClass
    val :json_field, T.hash(String, Untyped)
  end

  class OrderCreated
    include DataClass
    val :id, UUID
    val :sku, String
    val :quantity, Integer
  end

  class OrderChanged
    include DataClass
    val :id, UUID
    val :quantity, Integer
  end

  class OrderCanceled
    include DataClass
    val :id, UUID
  end

  OrderEvent = T.union(created: OrderCreated, changed: OrderChanged, canceled: OrderCanceled)

  OrderEventDiscriminated = T.union(created: OrderCreated, changed: OrderChanged, canceled: OrderCanceled)

  class MessageCamelCase
    include DataClass
    val :fieldInt, Integer
  end

  OrderEventCamelCase = T.union(createdOrder: OrderCreated, changedOrder: OrderChanged, canceledOrder: OrderCanceled)
end

module TestService::V2
  class Message
    include DataClass
    val :field, String
  end
end
