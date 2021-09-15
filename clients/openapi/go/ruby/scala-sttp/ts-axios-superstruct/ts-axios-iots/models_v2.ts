/* eslint-disable @typescript-eslint/camelcase */
/* eslint-disable @typescript-eslint/no-magic-numbers */
import * as t from './io-ts'

export const TMessage = t.interface({
    bool_field: t.boolean,
    string_field: t.string,
})

export type Message = t.TypeOf<typeof TMessage>
