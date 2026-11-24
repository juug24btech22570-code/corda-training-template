
package net.corda.training.state

import net.corda.core.contracts.Amount
import net.corda.core.contracts.BelongsToContract
import net.corda.core.contracts.LinearState
import net.corda.core.identity.Party
import net.corda.core.identity.UniqueIdentifier
import net.corda.training.contract.IOUContract
import java.util.Currency

@BelongsToContract(IOUContract::class)
data class IOUState(
    val amount: Amount<Currency>,
    val lender: Party,
    val borrower: Party,
    val paid: Amount<Currency> = Amount(0, amount.token),
    override val linearId: UniqueIdentifier = UniqueIdentifier()
) : LinearState {

    override val participants: List<Party>
        get() = listOf(lender, borrower)

    // Task 10
    fun pay(amountToPay: Amount<Currency>): IOUState {
        return copy(paid = paid + amountToPay)
    }

    // Task 11
    fun withNewLender(newLender: Party): IOUState {
        return copy(lender = newLender)
    }
}
