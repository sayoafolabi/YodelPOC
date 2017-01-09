Feature: Customer can track a specific number
		In order to know the status of my parcel
		As a customer
		I want to be able to track my parcel on Yodel website with a given Yodel tracking number
		
Scenario: Track using a Yodel customer number
		Given I navigate to Yodel homepage
		When I enter Yodel tracking number
		And I enter my postcode
		And I click on track button
		Then the corresponding tracking information for my tracking number is displayed
		 