from abc import ABC, abstractmethod

class Comparable(ABC):

    @abstractmethod
    def compare_to(self, other):
        pass

class Example(ABC):

    @staticmethod
    def sort(a):
        pass

    @staticmethod
    def less(v, w):
        return v.compare_to(w) < 0

    @staticmethod
    def exchange(a, i, j):
        t = a[i]
        a[i] = a[j]
        a[j] = t

    @staticmethod
    def show(a):
        print(a)

    @classmethod
    def is_sorted(cls, a):
        for i in range(1, len(a)):
            if cls.less(a[i], a[i-1]):
                return False
        return True
