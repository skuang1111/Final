package rocket.app.view;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)

    @FXML
    private TextField txtIncome;
    @FXML
    private TextField txtExpenses;
    @FXML
    private TextField txtCreditScore;
    @FXML
    private TextField txtHouseCost;
    @FXML
    private ComboBox cmbTerm;
    @FXML
    private Label lblMortgagePayment;
    @FXML
    private Label cmbTermLabel;
    @FXML
    private Label rateLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label creditScoreLabel;
    @FXML
    private Label dowmPaymentLabel;
    @FXML
    private Label incomeLabel;
    @FXML
    private Label expensesLabel;
    @FXML
    private Label MonthlyPaymentLabel;
    @FXML
    private Button btnPayment;
    @FXML
    private Label errorMessagesLabel;
    
    


	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq

		rateLabel.setText(Double.toString(lq.getdRate()));
		amountLabel.setText(Double.toString(lq.getdAmount()));
		creditScoreLabel.setText(Integer.toString(lq.getiCreditScore()));
		dowmPaymentLabel.setText(Integer.toString(lq.getiDownPayment()));
		incomeLabel.setText(Double.toString(lq.getIncome()));
		expensesLabel.setText(Double.toString(lq.getExpenses()));
		
		
		a.setLoanRequest(lq);
		
		
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		lRequest.setiTerm(Integer.parseInt(cmbTerm.getPromptText()));
		lRequest.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lRequest.setIncome(Double.parseDouble(txtIncome.getText()));
		lRequest.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lRequest.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		
		 ComboBox<Integer> cmbTerm = new ComboBox<Integer>();
		 cmbTerm.getItems().addAll(15, 30);
		
		String rate = String.format("%1$,.2f", Math.abs(lRequest.getdRate()));
		lblMortgagePayment.setText(rate);
		String pmt = String.format("%1$,.2f", Math.abs(lRequest.getdPayment()));
		lblMortgagePayment.setText(pmt);
		
		int term = lRequest.getiTerm();
		double payment = lRequest.getdPayment();
		double income = lRequest.getIncome();
		double expenses = lRequest.getExpenses();
		
		if (lRequest.getdRate() == 0) {
			rateLabel.setText("nothing");
			MonthlyPaymentLabel.setText("nothing");
		} 
		else {
			rateLabel.setText(rate);
			if ((payment * 12 * term < (income - expenses * 12) * term)) {
				MonthlyPaymentLabel.setText(pmt);
			} else {	
				MonthlyPaymentLabel.setText("too high");
			}
		}
		
	}
}
