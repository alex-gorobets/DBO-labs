'''
@author: alsp
'''
from views.LoginView import LoginView

class LoginController(object):
    '''
    This is controller for login screen
    '''
        
    def run(self):
        print("loading login controller")
        self.view = LoginView()
        self.view.run()