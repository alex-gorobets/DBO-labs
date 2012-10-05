from controllers.LoginController import LoginController

class MainController(object):
    '''
    This is main class of project. It runs another controllers.
    Each controller has a reference for this class and can launch another
    controller by method 'run'
    '''
    
    '''
    Project entry point that launches Login controller
    '''
    def __init__(self):
        self.run(LoginController(self))
        
    '''
    Launches controller, that specified as second argument
    '''
    def run(self, controller):
        controller.run()
        
app = MainController()