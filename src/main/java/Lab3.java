import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab3 {
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JFormattedTextField passwordField;
	private JTextArea wordTextArea;
	private JFormattedTextField PhoneNumberTextField;
	private JTextField fullNameField;
	private JTextField streetAddressField;
	private JLabel wordCountLabel;
	private JTextArea passwordOutputTextArea;
	private JTextField idTextField;

	private Lab3() {
		try {
			PhoneNumberTextField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(###) ###-####")));

			wordTextArea.getDocument().addDocumentListener(new CDocumentListener(documentUpdateTemplate -> {
				String text = wordTextArea.getText();

				int count = 0;
				Matcher matcher = Pattern.compile("(\\w+)").matcher(text);
				while(matcher.find())
					count++;

				wordCountLabel.setText("Word Count: " + count);
			}));

			passwordField.getDocument().addDocumentListener(new CDocumentListener(documentUpdateTemplate -> {
				String error = "";

				String password = passwordField.getText();

				if(password.length() < 6 || password.length() > 10)
					error += "Password must be between 6 and 10 chars\n";

				if(!Pattern.compile("\\d").matcher(password).find())
					error += "Passwords must contain a number\n";

				if(!Pattern.compile("[A-Za-z]").matcher(password).find())
					error += "Passwords must contain a letter\n";

				if(!Pattern.compile("[A-Z]").matcher(password).find())
					error += "Passwords must contain a uppercase letter\n";

				if(error.equals(""))
					error = "Password meets requirements!";

				passwordOutputTextArea.setText(error);
			}));

			fullNameField.getDocument().addDocumentListener(new CDocumentListener(documentUpdateTemplate -> updateID()));
			streetAddressField.getDocument().addDocumentListener(new CDocumentListener(documentUpdateTemplate -> updateID()));

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void updateID() {
		String name = fullNameField.getText();
		String address = streetAddressField.getText();

		String output = "";
		Matcher matcher = Pattern.compile("(\\b[a-zA-Z])").matcher(name);
		while(matcher.find())
			output += matcher.group().toUpperCase();
		matcher = Pattern.compile("(\\d)").matcher(address);
		while(matcher.find())
			output += matcher.group();

		idTextField.setText(output);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Lab3");
		frame.setContentPane(new Lab3().tabbedPane1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}