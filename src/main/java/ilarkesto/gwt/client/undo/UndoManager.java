package ilarkesto.gwt.client.undo;

import ilarkesto.core.logging.Log;

import java.util.LinkedList;
import java.util.List;

public class UndoManager {

	private static final Log LOG = Log.get(UndoManager.class);

	private int maxOperations = 5;
	private List<AUndoOperation> operations = new LinkedList<AUndoOperation>();

	public void add(AUndoOperation operation) {
		operations.add(0, operation);
		while (operations.size() > maxOperations) {
			operations.remove(operations.size() - 1);
		}
	}

	public void undo(AUndoOperation operation) {
		LOG.info("Undo:", operation);
		operation.undo();
		operations.remove(operation);
	}

	public List<AUndoOperation> getOperations() {
		return operations;
	}

	public void clear() {
		operations.clear();
	}
}
