package ch.tutteli.atrium.logic.impl.creating.iterablelike.contains.creators

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.inAnyOrder
import ch.tutteli.atrium.api.fluent.en_GB.only
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour

class InOrderOnlyGroupedValuesAssertionCreator<E, in T : Iterable<E>>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E, T, E>(searchBehaviour),
    InOrderOnlyMatcher<E, E> by InOrderOnlyValueMatcher() {

    override fun Expect<List<E>>.createSublistAssertion(groupOfSearchCriteria: List<E>) {
        val inAnyOrderOnly = contains.inAnyOrder.only
        addAssertion(ExpectImpl.iterable.contains.valuesInAnyOrderOnly(inAnyOrderOnly, groupOfSearchCriteria))
    }
}
