require "date"
require "emery"

module TestService
  class Message
    include DataClass
    val :int_field, Integer
    val :string_field, String
  end

  class Choice
    include Enum
    define :first_choice, 'FIRST_CHOICE'
    define :second_choice, 'SECOND_CHOICE'
    define :third_choice, 'THIRD_CHOICE'
  end

  class Parameters
    include DataClass
    val :int_field, Integer
    val :long_field, Integer
    val :float_field, Float
    val :double_field, Float
    val :decimal_field, Float
    val :bool_field, Boolean
    val :string_field, String
    val :string_opt_field, T.nilable(String)
    val :string_defaulted_field, String
    val :string_array_field, T.array(String)
    val :uuid_field, UUID
    val :date_field, Date
    val :date_array_field, T.array(Date)
    val :datetime_field, DateTime
    val :enum_field, Choice
  end

  class UrlParameters
    include DataClass
    val :int_field, Integer
    val :long_field, Integer
    val :float_field, Float
    val :double_field, Float
    val :decimal_field, Float
    val :bool_field, Boolean
    val :string_field, String
    val :uuid_field, UUID
    val :date_field, Date
    val :datetime_field, DateTime
    val :enum_field, Choice
  end

  class Everything
    include DataClass
    val :body_field, Message
    val :float_query, Float
    val :bool_query, Boolean
    val :uuid_header, UUID
    val :datetime_header, DateTime
    val :date_url, Date
    val :decimal_url, Float
  end

  class OkResult
    include DataClass
    val :ok_result, String
  end

  class CreatedResult
    include DataClass
    val :created_result, String
  end

  class AcceptedResult
    include DataClass
    val :accepted_result, String
  end

  class ErrorLocation
    include Enum
    define :query, 'query'
    define :header, 'header'
    define :body, 'body'
    define :unknown, 'unknown'
  end

  class ValidationError
    include DataClass
    val :path, String
    val :code, String
    val :message, T.nilable(String)
  end

  class BadRequestError
    include DataClass
    val :message, String
    val :location, ErrorLocation
    val :errors, T.array(ValidationError)
  end

  class NotFoundError
    include DataClass
    val :message, String
  end

  class InternalServerError
    include DataClass
    val :message, String
  end
end

module TestService::V2
  class Message
    include DataClass
    val :bool_field, Boolean
    val :string_field, String
  end

  class ErrorLocation
    include Enum
    define :query, 'query'
    define :header, 'header'
    define :body, 'body'
    define :unknown, 'unknown'
  end

  class ValidationError
    include DataClass
    val :path, String
    val :code, String
    val :message, T.nilable(String)
  end

  class BadRequestError
    include DataClass
    val :message, String
    val :location, ErrorLocation
    val :errors, T.array(ValidationError)
  end

  class NotFoundError
    include DataClass
    val :message, String
  end

  class InternalServerError
    include DataClass
    val :message, String
  end
end
