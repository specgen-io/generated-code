import * as t from './../superstruct'

export const TMessage = t.type({
    field: t.string(),
})

export type Message = t.Infer<typeof TMessage>
