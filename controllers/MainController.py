'''
@author: alsp
'''
from controllers.LoginController import LoginController

if __name__ == '__main__':
    print("loading main controller")
    
    controller = LoginController()
    controller.run()