export function stringify(value: ScalarParam): string {
  if (value instanceof Date) {
      return value.toISOString()
  }
  return String(value)
}

export function stringifyX(value: ScalarParam | ScalarParam[]): string | string[] {
  if (Array.isArray(value)) {
    return value.map(stringify)
  } else {
    return stringify(value)
  }
}

type ScalarParam = string | number | boolean | Date
type ParamType = undefined | ScalarParam | ScalarParam[]

type ParamItem = [string, string]

export function strParamsItems(params: Record<string, ParamType>): ParamItem[] {
  return Object.entries(params)
      .filter(([key, value]) => value != undefined)
      .map(([key, value]): ParamItem[] => {
        if (Array.isArray(value)) {
          return value.map(item => [key, stringify(item)])
        } else {
          return [[key, stringify(value!)]]
        }
      })
      .flat()
}

export function strParamsObject(params: Record<string, ParamType>): Record<string, string | string[]> {
  return Object.keys(params)
      .filter(paramName => params[paramName] != undefined)
      .reduce((obj, paramName) => ({...obj, [paramName]: stringifyX(params[paramName]!)}), {} as Record<string, string | string[]>)
}