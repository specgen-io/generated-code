require "net/http"
require "net/https"
require "uri"
require "emery"

module TestService
  class EchoClient < TestService::BaseClient
    def echo_body_string(body:)
      url = @base_uri + '/echo/body_string'
      request = Net::HTTP::Post.new(url)
      request.add_field('Content-Type', 'text/plain')
      request.body = T.check_var('body', String, body)
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => response.body, :ok? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end

    def echo_body(body:)
      url = @base_uri + '/echo/body'
      request = Net::HTTP::Post.new(url)
      request.add_field('Content-Type', 'application/json')
      body_json = Jsoner.to_json(Message, T.check_var('body', Message, body))
      request.body = body_json
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => Jsoner.from_json(Message, response.body), :ok? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end

    def echo_query(int_query:, long_query:, float_query:, double_query:, decimal_query:, bool_query:, string_query:, string_opt_query:, string_defaulted_query: 'the default value', string_array_query:, uuid_query:, date_query:, date_array_query:, datetime_query:, enum_query:)
      query = TestService::StringParams.new
      query.set('int_query', Integer, int_query)
      query.set('long_query', Integer, long_query)
      query.set('float_query', Float, float_query)
      query.set('double_query', Float, double_query)
      query.set('decimal_query', Float, decimal_query)
      query.set('bool_query', Boolean, bool_query)
      query.set('string_query', String, string_query)
      query.set('string_opt_query', T.nilable(String), string_opt_query)
      query.set('string_defaulted_query', String, string_defaulted_query)
      query.set('string_array_query', T.array(String), string_array_query)
      query.set('uuid_query', UUID, uuid_query)
      query.set('date_query', Date, date_query)
      query.set('date_array_query', T.array(Date), date_array_query)
      query.set('datetime_query', DateTime, datetime_query)
      query.set('enum_query', Choice, enum_query)
      url = @base_uri + '/echo/query' + query.query_str
      request = Net::HTTP::Get.new(url)
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => Jsoner.from_json(Parameters, response.body), :ok? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end

    def echo_header(int_header:, long_header:, float_header:, double_header:, decimal_header:, bool_header:, string_header:, string_opt_header:, string_defaulted_header: 'the default value', string_array_header:, uuid_header:, date_header:, date_array_header:, datetime_header:, enum_header:)
      header = TestService::StringParams.new
      header.set('Int-Header', Integer, int_header)
      header.set('Long-Header', Integer, long_header)
      header.set('Float-Header', Float, float_header)
      header.set('Double-Header', Float, double_header)
      header.set('Decimal-Header', Float, decimal_header)
      header.set('Bool-Header', Boolean, bool_header)
      header.set('String-Header', String, string_header)
      header.set('String-Opt-Header', T.nilable(String), string_opt_header)
      header.set('String-Defaulted-Header', String, string_defaulted_header)
      header.set('String-Array-Header', T.array(String), string_array_header)
      header.set('Uuid-Header', UUID, uuid_header)
      header.set('Date-Header', Date, date_header)
      header.set('Date-Array-Header', T.array(Date), date_array_header)
      header.set('Datetime-Header', DateTime, datetime_header)
      header.set('Enum-Header', Choice, enum_header)
      url = @base_uri + '/echo/header'
      request = Net::HTTP::Get.new(url)
      header.params.each { |name, value| request.add_field(name, value) }
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => Jsoner.from_json(Parameters, response.body), :ok? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end

    def echo_url_params(int_url:, long_url:, float_url:, double_url:, decimal_url:, bool_url:, string_url:, uuid_url:, date_url:, datetime_url:, enum_url:)
      url_params = {
        'int_url' => T.check(Integer, int_url),
        'long_url' => T.check(Integer, long_url),
        'float_url' => T.check(Float, float_url),
        'double_url' => T.check(Float, double_url),
        'decimal_url' => T.check(Float, decimal_url),
        'bool_url' => T.check(Boolean, bool_url),
        'string_url' => T.check(String, string_url),
        'uuid_url' => T.check(UUID, uuid_url),
        'date_url' => T.check(Date, date_url),
        'datetime_url' => T.check(DateTime, datetime_url),
        'enum_url' => T.check(Choice, enum_url),
      }
      url = @base_uri + Stringify::set_params_to_url('/echo/url_params/{int_url}/{long_url}/{float_url}/{double_url}/{decimal_url}/{bool_url}/{string_url}/{uuid_url}/{date_url}/{datetime_url}/{enum_url}', url_params)
      request = Net::HTTP::Get.new(url)
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => Jsoner.from_json(UrlParameters, response.body), :ok? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end

    def echo_everything(uuid_header:, datetime_header:, body:, date_url:, decimal_url:, float_query:, bool_query:)
      query = TestService::StringParams.new
      query.set('float_query', Float, float_query)
      query.set('bool_query', Boolean, bool_query)
      header = TestService::StringParams.new
      header.set('Uuid-Header', UUID, uuid_header)
      header.set('Datetime-Header', DateTime, datetime_header)
      url_params = {
        'date_url' => T.check(Date, date_url),
        'decimal_url' => T.check(Float, decimal_url),
      }
      url = @base_uri + Stringify::set_params_to_url('/echo/everything/{date_url}/{decimal_url}', url_params) + query.query_str
      request = Net::HTTP::Post.new(url)
      header.params.each { |name, value| request.add_field(name, value) }
      request.add_field('Content-Type', 'application/json')
      body_json = Jsoner.to_json(Message, T.check_var('body', Message, body))
      request.body = body_json
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => Jsoner.from_json(Everything, response.body), :ok? => true, :forbidden? => false)
      when '403'
        OpenStruct.new(:forbidden => nil, :ok? => false, :forbidden? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end

    def same_operation_name()
      url = @base_uri + '/echo/same_operation_name'
      request = Net::HTTP::Get.new(url)
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => nil, :ok? => true, :forbidden? => false)
      when '403'
        OpenStruct.new(:forbidden => nil, :ok? => false, :forbidden? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end
  end

  class CheckClient < TestService::BaseClient
    def check_empty()
      url = @base_uri + '/check/empty'
      request = Net::HTTP::Get.new(url)
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => nil, :ok? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end

    def check_forbidden()
      url = @base_uri + '/check/forbidden'
      request = Net::HTTP::Get.new(url)
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => Jsoner.from_json(Message, response.body), :ok? => true, :forbidden? => false)
      when '403'
        OpenStruct.new(:forbidden => nil, :ok? => false, :forbidden? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end

    def same_operation_name()
      url = @base_uri + '/check/same_operation_name'
      request = Net::HTTP::Get.new(url)
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => nil, :ok? => true, :forbidden? => false)
      when '403'
        OpenStruct.new(:forbidden => nil, :ok? => false, :forbidden? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end
  end
end

module TestService::V2
  class EchoClient < TestService::BaseClient
    def echo_body(body:)
      url = @base_uri + '/v2/echo/body'
      request = Net::HTTP::Post.new(url)
      request.add_field('Content-Type', 'application/json')
      body_json = Jsoner.to_json(Message, T.check_var('body', Message, body))
      request.body = body_json
      response = @client.request(request)
      case response.code
      when '200'
        OpenStruct.new(:ok => Jsoner.from_json(Message, response.body), :ok? => true)
      else
        raise StandardError.new("Unexpected HTTP response code #{response.code}")
      end
    end
  end
end
