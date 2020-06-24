@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.logic.impl.creating.iterablelike.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders.NoOpCheckerOption

fun <E : Any, T : Iterable<E?>> _containsEntriesInAnyOrder(
    checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
    assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
): Assertion = createAssertionGroup(checkerOption, assertionCreators, ::InAnyOrderEntriesDeprecatedAssertionCreator)

fun <E : Any, T : Iterable<E?>> _containsEntriesInAnyOrderOnly(
    builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
    assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(
        checkerBuilder,
        assertionCreators,
        ::InAnyOrderOnlyEntriesDeprecatedAssertionCreator
    )
}

fun <E : Any, T : Iterable<E?>> _containsEntriesInOrderOnly(
    builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
    assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(
        checkerBuilder,
        assertionCreators,
        ::InOrderOnlyEntriesDeprecatedAssertionCreator
    )
}

fun <E : Any, T : Iterable<E?>> _containsEntriesInOrderOnlyGrouped(
    builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedSearchBehaviour>,
    groups: List<List<(AssertionPlant<E>.() -> Unit)?>>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(
        checkerBuilder,
        groups,
        ::InOrderOnlyGroupedEntriesDeprecatedAssertionCreator
    )
}

private fun <E, T : Iterable<E>, SC, S : IterableContains.SearchBehaviour> createAssertionGroupWithoutChecker(
    checkerOption: IterableContains.CheckerOption<E, T, S>,
    expected: List<SC>,
    factory: (S) -> IterableContains.Creator<T, SC>
): AssertionGroup {
    val creator = factory(checkerOption.containsBuilder.searchBehaviour)
    return creator.createAssertionGroup(checkerOption.containsBuilder.subjectProvider, expected)
}

private fun <E, T : Iterable<E>, SC, S : IterableContains.SearchBehaviour> createAssertionGroup(
    checkerOption: IterableContains.CheckerOption<E, T, S>,
    expected: List<SC>,
    factory: (S, List<IterableContains.Checker>) -> IterableContains.Creator<T, SC>
): AssertionGroup {
    val creator = factory(checkerOption.containsBuilder.searchBehaviour, checkerOption.checkers)
    return creator.createAssertionGroup(checkerOption.containsBuilder.subjectProvider, expected)
}
