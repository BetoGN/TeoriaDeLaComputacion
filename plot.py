import numpy as np
import matplotlib.pyplot as plt
import matplotlib


ejeX, ejeY = np.loadtxt("Coordenadas.txt", skiprows = 0 , usecols = [0,1] , unpack = True)
#print(ejeX,ejeY)
plt.plot(ejeX,ejeY,"g")

plt.title("Cantidad de #1 en numeros primos")
plt.xlabel("Cantidad de numeros primos")
plt.ylabel("Cantidad de #1")
plt.grid(True)

plt.show()
