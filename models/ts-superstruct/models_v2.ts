import * as t from './superstruct'

export const TMessage = t.object({
    field: t.string(),
})

export type Message = t.Infer<typeof TMessage>
