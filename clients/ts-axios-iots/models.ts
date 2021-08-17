/* eslint-disable @typescript-eslint/camelcase */
/* eslint-disable @typescript-eslint/no-magic-numbers */
import * as t from './io-ts'

export const TMessage = t.interface({
    int_field: t.number,
    string_field: t.string,
})

export type Message = t.TypeOf<typeof TMessage>

export enum Choice {
    FIRST_CHOICE = "FIRST_CHOICE",
    SECOND_CHOICE = "SECOND_CHOICE",
    THIRD_CHOICE = "THIRD_CHOICE",
}

export const TChoice = t.enum(Choice)
