from abc import abstractmethod

class AbstractRepository(object):

    @abstractmethod
    def add(self, obj):
        
    @abstractmethod
    def get(self, offset = 0, limit = 10):
        
    @abstractmethod
    def set(self, key, obj):
        
    @abstractmethod
    def remove(self, key):
        