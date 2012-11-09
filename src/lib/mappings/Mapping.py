""" Mapping is pair <Key, Offset in .db file> """
class Mapping :
    
    """ Creating new mapping """
    def __init__(self, key, offset):
        self.key = key
        self.offset = offset
    
    """ Getter for key """
    def getKey(self):
        return self.key
    
    """ Getter for offset
        return offset in database file """
    def getOffset(self):
        return self.getOffset()
    
    def __str__(self):
        "Mapping: <" + self.key + ", " + self.offset + ">"
    