/* eslint-disable @typescript-eslint/camelcase */
/* eslint-disable @typescript-eslint/no-magic-numbers */
import * as t from './io-ts'


export const TMessage = t.interface({
    field: t.string,
})

export type Message = t.TypeOf<typeof TMessage>
