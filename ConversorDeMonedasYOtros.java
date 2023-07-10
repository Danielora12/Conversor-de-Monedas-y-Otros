import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ConversorDeMonedasYOtros extends JFrame {
    private JPanel container;
    private JComboBox<String> conversionType;
    private JPanel currencyConverter;
    private JPanel temperatureConverter;
    private JPanel metricConverter;
    private JTextField currencyAmount;
    private JComboBox<String> currencyFrom;
    private JComboBox<String> currencyTo;
    private JButton convertCurrency;
    private JLabel currencyResult;
    private JTextField temperatureAmount;
    private JComboBox<String> temperatureFrom;
    private JComboBox<String> temperatureTo;
    private JButton convertTemperature;
    private JLabel temperatureResult;
    private JTextField metricAmount;
    private JComboBox<String> metricFrom;
    private JComboBox<String> metricTo;
    private JButton convertMetric;
    private JLabel metricResult;

    public ConversorDeMonedasYOtros() {
        setTitle("Conversor de Monedas y Otros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        container = new JPanel();
        container.setLayout(null);

        conversionType = new JComboBox<>(new String[] { "Seleccionar...", "Conversor de Monedas",
                "Conversor de Temperatura", "Conversor Métrico" });
        conversionType.setBounds(30, 20, 200, 25);
        conversionType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSelectedConverter();
            }
        });
        container.add(conversionType);

        currencyConverter = new JPanel();
        currencyConverter.setLayout(null);
        currencyConverter.setBounds(30, 60, 400, 250);
        currencyConverter.setVisible(false);

        JLabel currencyLabel = new JLabel("Conversor de Monedas");
        currencyLabel.setBounds(10, 10, 200, 25);
        currencyConverter.add(currencyLabel);

        currencyAmount = new JTextField();
        currencyAmount.setBounds(30, 50, 150, 25);
        currencyConverter.add(currencyAmount);

        currencyFrom = new JComboBox<>(new String[] { "COP", "USD", "EUR", "JPY", "ARS", "BRL", "CLP", "MXN" });
        currencyFrom.setBounds(30, 90, 100, 25);
        currencyConverter.add(currencyFrom);

        currencyTo = new JComboBox<>(new String[] { "USD", "EUR", "JPY", "ARS", "BRL", "CLP", "COP", "MXN" });
        currencyTo.setBounds(180, 90, 100, 25);
        currencyConverter.add(currencyTo);

        convertCurrency = new JButton("Convertir");
        convertCurrency.setBounds(30, 130, 100, 25);
        convertCurrency.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
        currencyConverter.add(convertCurrency);

        currencyResult = new JLabel();
        currencyResult.setBounds(30, 170, 250, 25);
        currencyConverter.add(currencyResult);

        container.add(currencyConverter);

        temperatureConverter = new JPanel();
        temperatureConverter.setLayout(null);
        temperatureConverter.setBounds(30, 60, 400, 250);
        temperatureConverter.setVisible(false);

        JLabel temperatureLabel = new JLabel("Conversor de Temperatura");
        temperatureLabel.setBounds(10, 10, 200, 25);
        temperatureConverter.add(temperatureLabel);

        temperatureAmount = new JTextField();
        temperatureAmount.setBounds(30, 50, 150, 25);
        temperatureConverter.add(temperatureAmount);

        temperatureFrom = new JComboBox<>(new String[] { "Celsius", "Fahrenheit", "Kelvin" });
        temperatureFrom.setBounds(30, 90, 100, 25);
        temperatureConverter.add(temperatureFrom);

        temperatureTo = new JComboBox<>(new String[] { "Fahrenheit", "Celsius", "Kelvin" });
        temperatureTo.setBounds(180, 90, 100, 25);
        temperatureConverter.add(temperatureTo);

        convertTemperature = new JButton("Convertir");
        convertTemperature.setBounds(30, 130, 100, 25);
        convertTemperature.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
        temperatureConverter.add(convertTemperature);

        temperatureResult = new JLabel();
        temperatureResult.setBounds(30, 170, 250, 25);
        temperatureConverter.add(temperatureResult);

        container.add(temperatureConverter);

        metricConverter = new JPanel();
        metricConverter.setLayout(null);
        metricConverter.setBounds(30, 60, 400, 250);
        metricConverter.setVisible(false);

        JLabel metricLabel = new JLabel("Conversor Métrico");
        metricLabel.setBounds(10, 10, 200, 25);
        metricConverter.add(metricLabel);

        metricAmount = new JTextField();
        metricAmount.setBounds(30, 50, 150, 25);
        metricConverter.add(metricAmount);

        metricFrom = new JComboBox<>(new String[] { "Centímetros", "Metros", "Kilómetros" });
        metricFrom.setBounds(30, 90, 100, 25);
        metricConverter.add(metricFrom);

        metricTo = new JComboBox<>(new String[] { "Metros", "Centímetros", "Kilómetros" });
        metricTo.setBounds(180, 90, 100, 25);
        metricConverter.add(metricTo);

        convertMetric = new JButton("Convertir");
        convertMetric.setBounds(30, 130, 100, 25);
        convertMetric.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertMetric();
            }
        });
        metricConverter.add(convertMetric);

        metricResult = new JLabel();
        metricResult.setBounds(30, 170, 250, 25);
        metricConverter.add(metricResult);

        container.add(metricConverter);

        add(container);
    }

    public void showSelectedConverter() {
        String selectedConversionType = (String) conversionType.getSelectedItem();
        currencyConverter.setVisible(selectedConversionType.equals("Conversor de Monedas"));
        temperatureConverter.setVisible(selectedConversionType.equals("Conversor de Temperatura"));
        metricConverter.setVisible(selectedConversionType.equals("Conversor Métrico"));
    }

    public void convertCurrency() {
        try {
            double amount = Double.parseDouble(currencyAmount.getText());
            String from = (String) currencyFrom.getSelectedItem();
            String to = (String) currencyTo.getSelectedItem();

            // Perform currency conversion
            double rate = getExchangeRate(from, to);
            double result = amount * rate;
            currencyResult.setText(String.format("%.2f %s = %.2f %s", amount, from, result, to));
        } catch (NumberFormatException e) {
            currencyResult.setText("Error: Ingresa un valor numérico");
        }
    }

    public void convertTemperature() {
        try {
            double amount = Double.parseDouble(temperatureAmount.getText());
            String from = (String) temperatureFrom.getSelectedItem();
            String to = (String) temperatureTo.getSelectedItem();

            // Perform temperature conversion
double result;
if (from.equals("Celsius")) {
    if (to.equals("Fahrenheit")) {
        result = (amount * 9/5) + 32;
    } else if (to.equals("Kelvin")) {
        result = amount + 273.15;
    } else {
        result = amount;
    }
} else if (from.equals("Fahrenheit")) {
    if (to.equals("Celsius")) {
        result = (amount - 32) * 5/9;
    } else if (to.equals("Kelvin")) {
        result = (amount - 32) * 5/9 + 273.15;
    } else {
        result = amount;
    }
} else if (from.equals("Kelvin")) {
    if (to.equals("Celsius")) {
        result = amount - 273.15;
    } else if (to.equals("Fahrenheit")) {
        result = (amount - 273.15) * 9/5 + 32;
    } else {
        result = amount;
    }
} else {
    result = amount;
}

            temperatureResult.setText(String.format("%.2f %s = %.2f %s", amount, from, result, to));
        } catch (NumberFormatException e) {
            temperatureResult.setText("Error: Ingresa un valor numérico");
        }
    }

    public void convertMetric() {
        try {
            double amount = Double.parseDouble(metricAmount.getText());
            String from = (String) metricFrom.getSelectedItem();
            String to = (String) metricTo.getSelectedItem();

            // Perform metric conversion
            double result;
            if (from.equals("Centímetros")) {
                if (to.equals("Metros")) {
                    result = amount / 100;
                } else if (to.equals("Kilómetros")) {
                    result = amount / 100000;
                } else {
                    result = amount;
                }
            } else if (from.equals("Metros")) {
                if (to.equals("Centímetros")) {
                    result = amount * 100;
                } else if (to.equals("Kilómetros")) {
                    result = amount / 1000;
                } else {
                    result = amount;
                }
            } else if (from.equals("Kilómetros")) {
                if (to.equals("Centímetros")) {
                    result = amount * 100000;
                } else if (to.equals("Metros")) {
                    result = amount * 1000;
                } else {
                    result = amount;
                }
            } else {
                result = amount;
            }

            metricResult.setText(String.format("%.2f %s = %.2f %s", amount, from, result, to));
        } catch (NumberFormatException e) {
            metricResult.setText("Error: Ingresa un valor numérico");
        }
    }

    private double getExchangeRate(String from, String to) {
        // Simulate API call or retrieve exchange rates from a database
        if (from.equals("COP") && to.equals("USD")) {
            return 0.00027;
        } else if (from.equals("USD") && to.equals("COP")) {
            return 3766.70;
        } else if (from.equals("EUR") && to.equals("USD")) {
            return 1.18;
        } else if (from.equals("USD") && to.equals("EUR")) {
            return 0.85;
        } else if (from.equals("JPY") && to.equals("USD")) {
            return 0.0091;
        } else if (from.equals("USD") && to.equals("JPY")) {
            return 109.92;
        } else {
            return 1.0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ConversorDeMonedasYOtros conversor = new ConversorDeMonedasYOtros();
                conversor.setVisible(true);
            }
        });
    }
}