package by.khmara.calculator.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.*;

import javax.swing.*;

public class Panel extends JPanel {

	private JButton buttons[] = new JButton[10];
	private JButton funcButtons[] = new JButton[9];
	private Font font = new Font("Segoe UI", Font.BOLD, 30);
	private Font font2 = new Font("Segoe UI", Font.ITALIC, 25);
	private JTextField output = new JTextField();

	char operator;
	double num1 = 0, num2 = 0, result = 0;

	Panel() {
		setLayout(null);
		setFocusable(true);
		grabFocus();

		funcButtons[0] = new JButton("%");
		funcButtons[0].setBounds(0, 500, 100, 100);
		funcButtons[1] = new JButton(".");
		funcButtons[1].setBounds(200, 500, 100, 100);
		funcButtons[2] = new JButton("=");
		funcButtons[2].setBounds(300, 400, 100, 200);
		funcButtons[2].setBackground(Color.WHITE);
		funcButtons[3] = new JButton("+");
		funcButtons[3].setBounds(300, 300, 100, 100);
		funcButtons[4] = new JButton("-");
		funcButtons[4].setBounds(300, 200, 100, 100);
		funcButtons[5] = new JButton("<");
		funcButtons[5].setBounds(300, 100, 100, 100);
		funcButtons[6] = new JButton("*");
		funcButtons[6].setBounds(200, 100, 100, 100);
		funcButtons[7] = new JButton("/");
		funcButtons[7].setBounds(100, 100, 100, 100);
		funcButtons[8] = new JButton("D");
		funcButtons[8].setBounds(0, 100, 100, 100);

		for (int i = 0; i <= 8; i++) {

			funcButtons[i].setFont(font2);
			funcButtons[i].setBorderPainted(false);
			funcButtons[i].setFocusPainted(false);
			add(funcButtons[i]);

		}

		buttons[0] = new JButton("0");
		buttons[0].setBounds(100, 500, 100, 100);
		buttons[0].setFont(font);
		buttons[0].setBorderPainted(false);
		buttons[0].setFocusPainted(false);
		add(buttons[0]);

		int temp = 7;
		
		for (int x = 0; x < 3; x++) {

			for (int y = 0; y < 3; y++) {

				buttons[temp - 3 * y] = new JButton(temp - 3 * y + "");
				buttons[temp - 3 * y].setBounds(100 * x, 200 + 100 * y, 100, 100);
				buttons[temp - 3 * y].setFont(font);
				buttons[temp - 3 * y].setBorderPainted(false);
				buttons[temp - 3 * y].setFocusPainted(false);
				add(buttons[temp - 3 * y]);
			}

			temp = temp + 1;
		}

		output.setBounds(0, 0, 400, 100);
		output.setEditable(false);
		output.setHorizontalAlignment(JTextField.RIGHT);
		output.setFont(font);
		add(output);

		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JButton b = (JButton) e.getSource();

				for (int i = 0; i < 10; i++) {

					if (b == buttons[i]) {
						output.setText(output.getText() + b.getText());
					}
				}

				if (b == funcButtons[0]) {
					operator = '%';
					output.setText((Double.parseDouble(output.getText()) / 100) + "");
				}

				if (b == funcButtons[1]) {
					if (output.getText().isEmpty()) {
						output.setText("0.");
					} else if (output.getText().contains(funcButtons[1].getText())) {
						return;
					} else {
						output.setText(output.getText() + ".");
					}
				}

				if (b == funcButtons[4]) {

					if (output.getText().isEmpty()) {

						output.setText(output.getText() + b.getText());

					} else if (output.getText().equals("-")) {

						output.setText(output.getText());

					} else {

						num1 = Double.parseDouble(output.getText());
						operator = '-';
						output.setText("");
					}
				}

				if (b == funcButtons[3]) {
					operator = '+';
					if (output.getText().isEmpty() || output.getText().equals("-")) {

						output.setText(output.getText());

					} else {

						num1 = Double.parseDouble(output.getText());
						output.setText("");
					}
				}

				if (b == funcButtons[6]) {
					operator = '*';

					if (output.getText().isEmpty() || output.getText().equals("-")) {

						output.setText(output.getText());

					} else {

						num1 = Double.parseDouble(output.getText());
						output.setText("");
					}
				}

				if (b == funcButtons[7]) {
					operator = '/';
					if (output.getText().isEmpty() || output.getText().equals("-")) {

						output.setText(output.getText());

					} else {

						num1 = Double.parseDouble(output.getText());
						output.setText("");
					}
				}

				if (b == funcButtons[2]) {

					if (output.getText().isEmpty() || output.getText().equals("-") || num1 == 0) {
						output.setText(output.getText());
					} else {

						num2 = Double.parseDouble(output.getText());

						switch (operator) {
						case '+':
							result = num1 + num2;
							break;
						case '-':
							result = num1 - num2;
							break;
						case '*':
							result = num1 * num2;
							break;
						case '/':
							result = num1 / num2;
							break;

						}

						if (num2 != 0) {
							output.setText(String.valueOf(result));

						} else {

							output.setText(output.getText());
						}

						num1 = 0;
						num2 = 0;
						result = 0;

					}
				}

				if (b == funcButtons[8]) {
					output.setText("");
					num1 = 0;
					num2 = 0;
					result = 0;
				}

				if (b == funcButtons[5]) {

					if (output.getText().length() > 0) {
						output.setText(output.getText().substring(0, output.getText().length() - 1));
					} else
						return;
				}

			}
		};

		for (JButton b : buttons) {
			b.addActionListener(l);
		}

		for (JButton b : funcButtons) {
			b.addActionListener(l);
		}

// ability to write numbers from keyboard

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				char symbol = e.getKeyChar();

				if (!Character.isDigit(symbol)) {
					return;
				}

				output.setText(output.getText() + symbol);
			}
		});
	}
}
