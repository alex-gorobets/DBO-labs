from views.LoginView import LoginView
import hashlib

class LoginController(object):
    '''
    This is controller for login screen
    '''
    
    def __init__(self, controller):
        self.controller = controller
        
    def run(self):
        print("loading login controller")
        self.view = LoginView()
        
        self.view.setButtonCallback(self.signIn)
        self.view.run()
            
    def signIn(self):
        print("sign in")
        
        passwordHash = hashlib.md5(self.view.getPassword())
        account = self.view.getLogin() + ":" + passwordHash.hexdigest()
        
        print(account)
        print(self.getAccounts())
        
        if account in self.getAccounts():
            print("Logged in")
            self.view.showError("Welcome")
        else:
            self.view.showError("Login or password is incorrect")
            
    def getAccounts(self):
        accounts = open("../data/account.db", "r", ).readlines()
        
        for i in range(len(accounts)):
            accounts[i] = accounts[i].strip('\n\xef\xbb\xbf')
            
        return accounts
        