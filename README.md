# Personal Finance Tracker

## by Braeden Fong


## 1. What will the project do?

This project is meant to help users manage their 
personal finances and help set saving goals.
The app will provide a straightforward interface
to track income, expenses, and savings, helping users
save money effectively, and also track income and expenses.



## 2. Who will use it?
 This app is designed for people who want a simple way to keep track of their 
personal finances, such as income, expenses, and savings. If you are comfortable using desktop-based applications,
this app is suitable for you.

## 3. Why is this project of interest to you?
This project connects to my major as a Business and Computer Science student,
and also combines my passion for personal finances and software
development. I hope to be able to provide a user-friendly
tool that helps people manage their personal finance simply yet effectively. This project will provide me
valuable experience in developing desktop applications that implement
features related to finance and goal setting.

## User Stories:

- As a user, I want to be able to add my income and categorize it, such as salary, dividend, etc.
- As a user, I want to be able to add my expenses, including the description, and amount
- As a user, I want to be able to track my savings contributions, to see the progress I'm making towards saving goals
- As a user, I want to be able to set savings goals with specific target amounts
- As a user, I want to be able to see a summary of all financial fields
- As a user, I want to be able to save the state of all my financial attributes
- As a user, I want to be able to reload the save state of my finances 

## Instructions for Grader

- To add an Income, click the "Add Income" button. A dialog will prompt you to enter the source and amount of the income.
- To add an Expense, click the "Add Expense" button. A dialog will prompt you to enter the description and amount of the expense.
- To add a Savings Goal, click the "Add Savings Goal" button. A dialog will prompt you to enter the name and the target amount of the goal.
- To contribute to a Savings Goal, click the "Contribute to Savings Goal" button. A dialog will prompt you to enter the name of the goal and how much you want to contribute.
- To save the state of the application, click the "Save Data" button. This will save the financial data, including expenses, incomes, and savings goals, to a JSON file.
- To load a previously saved state, click the "Load Data" button. This will load the financial data from a JSON file and update the application with the loaded data.
- The visual component, a splash screen, will appear when you launch the application. It displays an animated GIF located in the `data` package.

Note: The balance is managed automatically based on the added incomes, expenses, and contributions to savings goals.


Phase 4 - Refactoring Paragraph:

If I were to refactor my code, I would separate out my JsonReader and JsonWriter into fields in my FinancialTracker class.
By not doing this, I had to declare many new JsonReaders and JsonWriters in my code, and it caused other classes to produce
unnecessary code and methods. What I would do is I would instantiate the JsonReader and JsonWriter instances in the 
constructor of the FinancialTracker class, and pass the file paths to them. Then, loadData and saveData methods 
in the GUI class could then interact with the JsonReader and JsonWriter fields of the FinancialTracker class.


## Example of EventLog
Thu Aug 10 20:09:42 PDT 2023
Income added
Thu Aug 10 20:09:47 PDT 2023
Expense added
Thu Aug 10 20:09:51 PDT 2023
Savings goal added
Thu Aug 10 20:09:55 PDT 2023
Contributed to Savings
Thu Aug 10 20:09:57 PDT 2023
Data saved



