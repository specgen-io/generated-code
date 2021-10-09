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
    define :first_choice, 'One'
    define :second_choice, 'Two'
    define :third_choice, 'Three'
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
    val :json_field, T.hash(String, Unknown)
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

  class OrderEvent
    include TaggedUnion
    tag :created, OrderCreated
    tag :changed, OrderChanged
    tag :canceled, OrderCanceled
  end

  class OrderEventDiscriminated
    include TaggedUnion
    with_discriminator "_type"
    tag :created, OrderCreated
    tag :changed, OrderChanged
    tag :canceled, OrderCanceled
  end

  class MessageCamelCase
    include DataClass
    val :fieldInt, Integer
  end

  class OrderEventCamelCase
    include TaggedUnion
    tag :createdOrder, OrderCreated
    tag :changedOrder, OrderChanged
    tag :canceledOrder, OrderCanceled
  end
end

module TestService::V2
  class Message
    include DataClass
    val :field, String
  end
end
