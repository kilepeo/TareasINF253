import re

variables_totales = {}
flag = None
flag2 = None
flag3 = None

'''
***
r_code
***
Parametro 1 : str (archivo) - Nombre del archivo de código a leer
***
list[str]
***
Lee el archivo de código y retorna sus líneas como una lista de cadenas.
'''

def r_code(archivo):
    with open(archivo, 'r') as leer:
        lineas = leer.readlines()
    return lineas

'''
***
procesar_linea_leida
***
Parametro 1 : str (linea) - Línea actual del archivo de código
Parametro 2 : iter(list[str]) (text) - Iterador sobre las líneas del archivo
Parametro 3 : bool or None (flag) - Bandera para controlar la ejecución del primer bloque if
Parametro 4 : bool or None (flag2) - Bandera para controlar la ejecución del segundo bloque if si es que se presenta
Parametro 5 : bool or None (flag3) - Bandera para controlar la ejecución del tercer bloque if si es que se presenta
Parametro 6 : int (i) - Número de la línea actual
***
bool o None, int
***
Procesa una línea del archivo de código, identificando y ejecutando diferentes tipos de instrucciones dependiendo del lenguaje regular.
Devuelve True si realiza el match, las flags actualizadas y el número de línea.
Si no realiza match, retornará False.
'''

def procesar_linea_leida(linea, text, flag, flag2, flag3, i):
    ifs = 0
    elses = 0
    upp_bracket = 0
    low_bracket = 0

    declaradores = re.compile(r'^\s*DEFINE\s+(\$_[A-Z][A-Za-z]*)\s*$')
    oper_un = re.compile(r'^\s*DP\s+(\$_[A-Z][A-Za-z]*)\s+ASIG\s+(.+)\s*$')
    oper_bin = re.compile(r'^\s*DP\s+(\$_[A-Z][A-Za-z]*)\s+(.+)\s+(.+)\s+(.+)\s*$')
    condicionales_if = re.compile(r'^\s*if\s*\((\$_[A-Z][A-Za-z]*)\)\s*\{\s*$')
    condicionales_else = re.compile(r'^\s*\}\s*else\s*\{\s*$')
    condicionales_fin = re.compile(r'^\s*\}\s*$')
    mostrador = re.compile(r'^\s*MOSTRAR\s*\((\$_[A-Z][A-Za-z]*)\)\s*$')

    if declaradores.match(linea):
        if not variables(declaradores.match(linea).groups()[0], i):
            return False, flag, flag2, flag3, i
        
    elif oper_un.match(linea):
        variable_entrada, value = oper_un.match(linea).groups()
        if not asignacion(variable_entrada, value, i):
            return False, flag, flag2, flag3, i

    elif oper_bin.match(linea):
        variable_entrada, op, op1, op2 = oper_bin.match(linea).groups()
        if not operaciones_bin(variable_entrada, op, op1, op2, i):
            return False, flag, flag2, flag3, i

    elif mostrador.match(linea):
        variable_entrada = mostrador.match(linea).groups()[0]
        if not mostrar_variable(variable_entrada, i):
            return False, flag, flag2, flag3, i

    elif condicionales_if.match(linea):
        cond_var = condicionales_if.match(linea).group(1)
        if valores(cond_var):
            if flag is None:
                flag = True
            elif flag is True and flag2 is None:
                flag2 = True
            elif flag2 is True and flag3 is None:
                flag3 = True
            return True, flag, flag2, flag3, i
        else:
            ifs += 1
            while ifs != elses:
                linea = next(text, None)
                i += 1 
                if "if" in linea:
                    ifs += 1
                elif "else" in linea:
                    elses += 1
            if flag is None:
                flag = False
            elif flag2 is None:
                flag2 = False
            elif flag3 is None:
                flag3 = False 
            return True, flag, flag2, flag3, i

    elif condicionales_else.match(linea):
        if flag or (flag2 is not None and flag2) or (flag3 is not None and flag3):
            upp_bracket += 1
            while upp_bracket != low_bracket:
                linea = next(text, None)
                i += 1 
                if linea is None:
                    break
                if "{" in linea:
                    upp_bracket += 1
                elif "}" in linea:
                    low_bracket += 1
            return True, flag, flag2, flag3, i
        else:
            return True, flag, flag2, flag3, i


    elif condicionales_fin.match(linea):
        if flag3 is not None:
            flag3 = None
        elif flag2 is not None:
            flag2 = None
        else:
            flag = None
        return True, flag, flag2, flag3, i
    else:
        print(f"Error de sintaxis: Mala sintaxis en la línea {i}")
        return False, flag, flag2, flag3, i

    return True, flag, flag2, flag3, i

'''
***
variables
***
Parametro 1 : str (variable_entrada) - Nombre de la variable a definir
Parametro 2 : int (i) - Número de la línea actual
***
bool
***
Define una nueva variable en el diccionario `variables_totales` si no está ya definida.
Retorna `True` si la variable fue definida exitosamente, `False` si ya estaba definida.
'''

def variables(variable_entrada, i):
    if variable_entrada in variables_totales:
        print(f"Error durante ejecución en línea {i}: Variable {variable_entrada} ya definida")
        return False
    else:
        variables_totales[variable_entrada] = None
        return True

'''
***
asignacion
***
Parametro 1 : str (variable_entrada) - Nombre de la variable a la que se le asigna un valor
Parametro 2 : str (value) - Valor a asignar a la variable
Parametro 3 : int (i) - Número de la línea actual
***
bool
***
Asigna un valor a una variable existente en el diccionario `variables_totales`.
El valor puede ser un número entero, un booleano, un string entre `#`, o el valor de otra variable.
Retorna `True` si la asignación fue exitosa, `False` si la variable no está definida o el valor es inválido.
'''

def asignacion(variable_entrada, value, i):
    caracteres_especiales = re.compile(r'^[^!@#$%^&*(),.?":{}|<>#]+$')
    if variable_entrada not in variables_totales:
        print(f"Error durante ejecución en línea {i}: Variable {variable_entrada} no definida")
        return False
    else:
        if value.isdigit():
            variables_totales[variable_entrada] = int(value)
        elif value == 'True':
            variables_totales[variable_entrada] = True
        elif value == 'False':
            variables_totales[variable_entrada] = False
        elif value.startswith('#') and value.endswith('#'):
            if caracteres_especiales.match(value[1:-1]):
                variables_totales[variable_entrada] = value[1:-1]
            else:    
                print(f"Error de sintaxis en linea {i}: Caractér inválido para un string")
                return False
        elif value in variables_totales:
            variables_totales[variable_entrada] = variables_totales[value]
        else:
            print(f"Error de sintaxis: Valor inválido {value} para la variable {variable_entrada} en línea {i}")
            return False
        return True

'''
***
operaciones_bin
***
Parametro 1 : str (variable_entrada) - Nombre de la variable para almacenar el resultado
Parametro 2 : str (op) - Operador binario a utilizar (+, *, >, ==)
Parametro 3 : str (op1) - Primer operando
Parametro 4 : str (op2) - Segundo operando
Parametro 5 : int (i) - Número de la línea actual
***
bool
***
Realiza una operación binaria entre dos operandos y asigna el resultado a una variable.
Los operandos pueden ser enteros o cadenas. La operación puede ser suma, multiplicación, comparación mayor que o igualdad.
Retorna `True` si la operación se realizó con éxito, `False` si ocurre un error en la operación o los operandos.
'''

def operaciones_bin(variable_entrada, op, op1, op2, i):
    valor_1 = valores(op1)
    valor_2 = valores(op2)
    if valor_1 is not None and valor_2 is not None:
        # Asegúrate de que los tipos sean consistentes antes de operar
        if isinstance(valor_1, str) and isinstance(valor_2, str):
            if op == '+':
                variables_totales[variable_entrada] = valor_1 + valor_2
            else:
                print(f"Error de ejecución en línea {i}: El operador {op} es incompatible para este tipo de datos")
                return False
        elif (isinstance(valor_1, str) and isinstance(valor_2, int)) or (isinstance(valor_1, int) and isinstance(valor_2, str)):
            if op == '+':
                variables_totales[variable_entrada] = str(valor_1) + " " + str(valor_2)
            elif op == "==":
                variables_totales[variable_entrada] = valor_1 == valor_2
            else:
                print(f"Error de ejecución en línea {i}: La operación {op} es incompatible para este tipo de datos.")
                return False
        elif isinstance(valor_1, int) and isinstance(valor_2, int):
            if op == '+':
                variables_totales[variable_entrada] = valor_1 + valor_2
            elif op == '*':
                variables_totales[variable_entrada] = valor_1 * valor_2
            elif op == '>':
                variables_totales[variable_entrada] = valor_1 > valor_2
            elif op == '==':
                variables_totales[variable_entrada] = valor_1 == valor_2
            else:
                print(f"Error de ejecución en línea {i} : El operador {op} no está definido.")
                return False
        else:
            print(f"Error de ejecución en línea {i}: Los valores {valor_1} y {valor_2} no pueden ser operados")
            return False
        return True
    else:
        print(f"Error de sintaxis: Mala sintaxis en la línea {i}")
        return False

'''
***
mostrar_variable
***
Parametro 1 : str (variable_entrada) - Nombre de la variable cuyo valor se mostrará
Parametro 2 : int (i) - Número de la línea actual
***
bool
***
Muestra el valor de una variable en el archivo `output.txt`. 
Retorna `True` si la variable está definida y su valor fue mostrado, `False` si la variable no está definida.
'''

def mostrar_variable(variable_entrada, i):
    if variable_entrada in variables_totales:
        with open('output.txt', 'a') as funcion:
            funcion.write(f"{variables_totales[variable_entrada]}\n")
        return True
    else:
        print(f"Error de ejecución en línea {i}: La variable {variable_entrada} no está definida")
        return False

'''
***
valores
***
Parametro 1 : str (operando) - Operando del que se obtendrá el valor
***
int o bool o str o None
***
Obtiene el valor correspondiente a un operando. Puede ser el valor de una variable, un número entero, un booleano, o un string.
Retorna el valor del operando o `None` si el operando no es reconocido.
'''

def valores(operando):
    caracteres_especiales = re.compile(r'(.*)^[^!@#$%^&*(),.?":{}|<>#](.*)+$')
    if operando in variables_totales:
        return variables_totales[operando]
    elif operando.isdigit():
        return int(operando)
    elif operando == 'True':
        return True
    elif operando == 'False':
        return False
    elif operando.startswith('#') and operando.endswith('#'):
        if caracteres_especiales.match(operando[1:-1]):
            return None
        else:
            return operando[1:-1]
    else:
        return None

'''
***
main
***
No recibe parámetros.
***
None
***
Función principal que lee el archivo de código y procesa cada línea utilizando `procesar_linea_leida`.
Gestiona las flags globales y maneja los errores de ejecución. Si ocurre un error, borra el contenido del archivo de salida.
'''

def main():
    i = 0
    lineas = r_code('codigo.txt')
    text = iter(lineas)
    global flag, flag2, flag3
    for linea in text:
        i += 1
        result, flag, flag2, flag3, i = procesar_linea_leida(linea, text, flag, flag2, flag3, i)
main()
