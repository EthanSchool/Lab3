import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.function.Consumer;

public class CDocumentListener implements DocumentListener {
	private Consumer<DocumentUpdateTemplate> function;

	@Override
	public void insertUpdate(DocumentEvent e) {
		function.accept(null);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		function.accept(null);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		function.accept(null);
	}

	public CDocumentListener(Consumer<DocumentUpdateTemplate> f) {
		function = f;
	}
}