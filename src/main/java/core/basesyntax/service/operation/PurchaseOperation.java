package core.basesyntax.service.operation;

import core.basesyntax.db.FruitStorage;
import core.basesyntax.model.FruitTransaction;

public class PurchaseOperation implements OperationHandler {
    @Override
    public void updateStorage(FruitTransaction transaction) {
        if (FruitStorage.fruitStorage.get(transaction.getFruit()) == null) {
            throw new RuntimeException("Before entering data for the operation "
                    + transaction.getOperation() + ", you must enter the BALANCE operation of "
                    + transaction.getFruit() + " under the code 'b'");
        }
        if (FruitStorage.fruitStorage.get(transaction.getFruit())
                - transaction.getQuantity() < 0) {
            throw new RuntimeException("The balance of " + transaction.getFruit()
                    + " after the operation " + transaction.getOperation()
                    + " with quantity " + transaction.getQuantity() + " cannot be negative.");
        }
        FruitStorage.fruitStorage.put(transaction.getFruit(),
                FruitStorage.fruitStorage.get(transaction.getFruit())
                        - transaction.getQuantity());
    }
}
