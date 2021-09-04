import * as t from './superstruct'

export const TMessage = t.type({
    bool_field: t.boolean(),
    string_field: t.string(),
})

export type Message = t.Infer<typeof TMessage>
