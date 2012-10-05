# coding: utf-8
import Tkinter as Tk

class LoginView(Tk.Toplevel):
    '''
    This is view for login screen
    '''

    def __init__(self):
        self.root = Tk.Tk()
        self.root.withdraw()
        Tk.Toplevel.__init__(self, self.root)

        self.protocol('WM_DELETE_WINDOW', self.master.destroy)
 
        self.loginLabel = Tk.Label(self, text = 'Login')
        self.loginLabel.pack(side = 'top')
        self.loginField = Tk.Entry(self, text = '0', width = 10)
        self.loginField.pack(side = 'top')
        
        self.passwordLabel = Tk.Label(self, text = 'Password')
        self.passwordLabel.pack(side = 'top')
        self.passwordField = Tk.Entry(self, text = '1', width = 10)
        self.passwordField.pack(side = 'top')
                
        self.signInButton = Tk.Button(self, text = 'Sign in',width = 10)
        self.signInButton.pack(side = 'top', padx = 15, pady = 10)

    def run(self):
        print("login view rendering")
        self.root.mainloop()
        
    def setButtonCallback(self, callback):
        self.signInButton.config(command = callback);
        
    def showError(self, message):
        if hasattr(self, 'errorLabel'):
            self.errorLabel.config(text = message)
        else:
            self.errorLabel = Tk.Label(self, text = message)
            self.errorLabel.pack(side = 'top')
            
    def getLogin(self):
        return self.loginField.get()

    def getPassword(self):
        return self.passwordField.get()

        