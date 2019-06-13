package Game;

import Excepciones.*;
import Entidades.*;
import Tablero.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Juego principal.
 *
 * @authors razvanvc & DanielGG
 */
public class StartGaming {

    public static Casilla[][] tablero;
    public static int filas;
    public static int columnas;
    public static int turno;
    public static int dificultad;
    public static int nzombies;
    public static int TurnoSpawn[];
    public static int TurnoInicioZombies;
    public static boolean ZGana;
    public static boolean PGana;
    public static boolean StartGame;
    public static String comands[];
    public static int soles;
    private static boolean girasolColocado;
    private static boolean lanzaColocado;
    public static boolean victoriaPlantas;
    public static boolean victoriaZombies;
    private static int ZombiesEnPartida;
    public static int ZombiesSalidos;
    private static int ZombiesPorSalir;
    private static boolean ZombiesTablero;
    private static boolean nuezColocado;
    private static boolean petaColocado;

    /**
     * El cuerpo principal del juego.
     *
     * @param args
     */
    public static void main(String[] args) {

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); //Se guarda en una variable la entrada por teclado.
        String comando; //Inicializa la varibale comando a vacío.
        comando = ""; //Asignamos la varibale comando a vacío.
        String lineaComando;
        do { //Medianteun bucle do while pedimos un comando cada turno hasta introducir s para salir.
            try {
                System.out.println("");
                System.out.println("(Teclea ayuda para lista de comandos). <Enter> para pasar el turno");
                System.out.print("Comando: > ");
                lineaComando = entrada.readLine().toLowerCase(); //Leemos la linea de comando y la convertimos a minúscula.
                System.out.println();
                Arguments(lineaComando); //Separa en argumentos para su posterior utilización.

                switch (comands[0]) { //Según el comando introducido se mete en un caso del switch.
                    case "n":
                        InicioPartida(); //Inicia la patida.
                        break;
                    case "g":
                        PlantarGirasol(); //Coloca un girasol.
                        break;
                    case "l":
                        PlantarLanzaguisantes(); //Coloca un lanzaguisante.
                        break;
                    case "s":
                        System.exit(0); //Finaliza la sesión de juego.
                        break;
                    case "": //Si el juego está iniciado, pasa el turno.
                        PasarTurno();
                        break;
                    case "ayuda": //Se llama a la función ayuda.
                        Ayuda();
                        break;
                    case "nu":
                        PlantarNuez();
                        break;
                    case "p":
                        PlantarPetacereza();
                        break;
                    case "victoriaz": //Comando de consola para el testeo.
                        victoriaZombies = true;
                        break;
                    case "victoriap": //Comando de consola para el testeo.
                        victoriaPlantas = true;
                        break;
                    default:
                        System.out.println("Comando invalido");
                }
            } catch (IOException IOE) { //Al introducir un caracter no válido, se lanza una excepción.
                System.out.println("ERROR: Caracteres no validos");
            } catch (ExcepcionJuego | ExcepcionPlanta e) { //Gestiona las excepciones producidas por el juego o por las plantas.
                System.out.println(e.toString()); //Imprime el mensaje de excepción.
            }
        } while (!victoriaZombies && !victoriaPlantas); //Mientras no ganen los Zombies o las Plantas se seguirá ejecutando el bucle principal.
        if (victoriaZombies) { //Si ganan los Zombies
            System.out.println("Zombies Ganan");
        }
        if (victoriaPlantas) { //Si ganan los Plantas.
            System.out.println("Plantas Ganan");
        }
    }

    /**
     * @param linea, es la linea de codigo que introduce el usuario.
     * @return contador, que es el numero de argumentos que tiene el comando.
     */
    public static int ContarArgumentos(String linea) {
        StringTokenizer codigo = new StringTokenizer(linea, " "); //Éste método cuenta los argumentos del código introducido por el usuario fila los separa con el comando Tokenizer.
        int contador = 0;
        while (codigo.hasMoreTokens()) { //Mediante un bucle while "miramos" si hay más argumentos fila lo guardamos en una variable para su posterior uso.
            contador++;
            String token = codigo.nextToken();
        }
        if (contador == 0) { //En caso de que no haya argumentos, siempre devuelve el número 1 (int).
            contador++;
        }
        return contador;
    }

    /**
     * Separa en argumentos para su posterior utilización.
     *
     * @param lineaComando, código introducido por el usuario.
     * @throws ExcepcionJuego se "lanza" un error si el comando introducido es
     * inválido.
     */
    public static void Arguments(String lineaComando) throws ExcepcionJuego {
        StringTokenizer codigo = new StringTokenizer(lineaComando, " "); //Asigna el código introducido a una variable con el método Tokenizer fila luego llama al método ContarArgumentos.
        comands = new String[ContarArgumentos(lineaComando)]; //Iniciamos el array de strings de comandos, el tamaño depende de la cantidad de argumentos.
        while (codigo.hasMoreTokens()) { //Mediante este bucle while y el método ContarArgumentos, vamos asignando cada token a una posición del array.
            for (int i = 0; i < ContarArgumentos(lineaComando); i++) {
                String token = codigo.nextToken();
                comands[i] = token;
            }
        }
        if (comands.length == 1) { //Si la longitud del comando es 1
            if (comands[0] == null) { //Si además está vacío (null)
                comands[0] = ""; //Se le asigna como valor un espacio string vacío.
            }
        }
    }

    /**
     * Imprime el tablero por pantalla.
     *
     * @param tablero, es el tablero con los datos contenidos.
     */
    public static void Renderizar(Casilla[][] tablero) {
        int i = 0;
        int filaA = 0;
        for (Casilla[] fila : tablero) { //Imprime linea por linea las casillas.

            while (i < columnas) { //Imprime la linea que delimita por arriba todas las casillas de la fila.
                if (filaA == 0) {
                    System.out.print("-------");
                }
                i++;
            }
            if (filaA == 0) { //Si la fila es la primera, imprime ésta cadena de caracteres.
                System.out.print("-\n");
            }

            i = 0;
            for (Casilla casilla : fila) { //Imprime todas las casillas de una fila.
                System.out.print("| " + casilla.toString() + " ");
            }
            System.out.print("|\n");

            while (i < columnas) { //Imprime la linea que delimita por abajo todas las casillas de la fila.
                System.out.print("-------");
                i++;
            }

            System.out.print("-\n");
            i = 0;
            filaA++;
        }
        ZombiesPorSalir = nzombies - ZombiesSalidos;
        System.out.println("Dificultad =" + dificultad + " Turno = " + turno
                + " Soles = " + soles + " Zombies por salir = " + ZombiesPorSalir);
//        System.out.println("Victoria Zombies = " + victoriaZombies);
//        System.out.println("Victoria Plantas = " + victoriaPlantas);
    }

    /**
     * Imprime el tablero por pantalla para MAC.
     *
     * @param tablero, es el tablero con los datos contenidos.
     */
    public static void RenderizarMAC(Casilla[][] tablero) {
        int i = 0;
        int filaactual = 0;
        //Imprime linea por linea las casillas.
        for (Casilla[] fila : tablero) {
            //Imprime la linea que delimita por arriba todas las casillas de la fila.
            if (filaactual == 0) {
                System.out.print("╔");
                while (i < columnas) {
                    if (i == columnas - 1) {
                        System.out.print("══════╗");
                    } else {
                        System.out.print("══════╦");
                    }
                    i++;
                }
                System.out.print("\n");
            }
            //Imprime todas las casillas de una fila.

            i = 0;
            for (Casilla casilla : fila) {
                System.out.print("║ " + casilla.toString() + " ");
            }
            System.out.print("║\n");
            //Imprime la linea que delimita por abajo todas las casillas de la fila.
            if (filaactual == filas - 1) {
                System.out.print("╚");
                while (i < columnas) {
                    if (i == columnas - 1) {
                        System.out.print("══════╝");
                        i++;
                    } else {
                        System.out.print("══════╩");
                        i++;
                    }
                }
            } else {
                System.out.print("╠");
                while (i < columnas) {
                    if (i == columnas - 1) {
                        System.out.print("══════╣");
                        i++;
                    } else {
                        System.out.print("══════╬");
                        i++;
                    }
                }
            }
            System.out.print("\n");
            i = 0;
            filaactual++;
        }
        ZombiesPorSalir = nzombies - ZombiesSalidos;
        System.out.println("Dificultad =" + dificultad + " Turno = " + turno
                + " Soles = " + soles + " Zombies por salir = " + ZombiesPorSalir);
        System.out.println("Victoria Zombies = " + victoriaZombies);
        System.out.println("Victoria Plantas = " + victoriaPlantas);
    }

    /**
     * Se inicializa el número de zombies y el turno de spawn según la
     * dificultad introducida por el usuario.
     */
    public static void IniciarZombies() {
        switch (dificultad) {
            case 1: //Facil
                nzombies = 5;
                TurnoInicioZombies = 10;
                break;
            case 2: //Normal
                nzombies = 15;
                TurnoInicioZombies = 7;
                break;
            case 3: //Dificil
                nzombies = 25;
                TurnoInicioZombies = 5;
                break;
            case 4: //Imposible
                nzombies = 50;
                TurnoInicioZombies = 5;
                break;
        }

        TurnoSpawn = new int[nzombies]; //Inicializamos el array de los turnos de spawn del tamaño de nzombies.
        for (int i = 0; i < nzombies; i++) { //Se genera de forma aleatoria el turno de aparición de los zombies hasta el máximo indicado por la dificultad.
            TurnoSpawn[i] = getRandomNumberInRange(TurnoInicioZombies, 30);
        }
        Arrays.sort(TurnoSpawn); //Ordenamos el array.
    }

    /**
     * Mediante un bucle do while y tres if anidados la función va colocando a
     * los zombies, según el turno de spawn asignado anteriormente y de forma
     * aleatoria, mediante las posiciones del tablero con las filas y columnas
     * introducidas por el usuario.
     */
    public static void SpawnZombie() {
        int i = 0;
        do {
            if (ZombiesSalidos >= nzombies) { //Si los ZombiesSalidos son mayores o iguales al nzombies totales, se sale del bucle.
                return;
            } else {
                int zombieElegido = getRandomNumberInRange(0, 2);
                switch (zombieElegido + 1) {
                    case 1:
                        if (turno >= TurnoSpawn[i]) { //Si el turno de juego es mayor o igual al TurnoSpawn
                            int filaZombie = getRandomNumberInRange(0, filas - 1); //Guardamos en una variable un número aleatorio según el rango.
                            if (!tablero[filaZombie][columnas - 1].isOcupado()) { //Si no está ocupada la casilla final del tablero, con la coordenada generada anteriormente.
                                Zombie z = new Zombie_Comun(); //Generamos un zombie.

                                z.setPosFila(columnas - 1); //Fila del Zombie.
                                z.setPosColumna(filaZombie); //Columna del Zombie.

                                Casilla c = new Casilla(z); //Nueva Casilla que contiene Zombie.
                                tablero[filaZombie][columnas - 1] = c; //Asignamos al tablero la casilla creada.
                                ZombiesSalidos += 1; //Aumentamos en 1 los zombies que han salido.
                            } else {
                                TurnoSpawn[i] += 1; //El turno de spawn aumenta en 1.
                            }
                        }
                        break;
                    case 2:
                        if (turno >= TurnoSpawn[i]) { //Si el turno de juego es mayor o igual al TurnoSpawn
                            int filaZombie = getRandomNumberInRange(0, filas - 1); //Guardamos en una variable un número aleatorio según el rango.
                            if (!tablero[filaZombie][columnas - 1].isOcupado()) { //Si no está ocupada la casilla final del tablero, con la coordenada generada anteriormente.
                                Zombie z = new Zombie_Caracubo(); //Generamos un zombie.

                                z.setPosFila(columnas - 1); //Fila del Zombie.
                                z.setPosColumna(filaZombie); //Columna del Zombie.

                                Casilla c = new Casilla(z); //Nueva Casilla que contiene Zombie.
                                tablero[filaZombie][columnas - 1] = c; //Asignamos al tablero la casilla creada.
                                ZombiesSalidos += 1; //Aumentamos en 1 los zombies que han salido.
                            } else {
                                TurnoSpawn[i] += 1; //El turno de spawn aumenta en 1.
                            }
                        }
                        break;
                    case 3:
                        if (turno >= TurnoSpawn[i]) { //Si el turno de juego es mayor o igual al TurnoSpawn
                            int filaZombie = getRandomNumberInRange(0, filas - 1); //Guardamos en una variable un número aleatorio según el rango.
                            if (!tablero[filaZombie][columnas - 1].isOcupado()) { //Si no está ocupada la casilla final del tablero, con la coordenada generada anteriormente.
                                Zombie z = new Zombie_Deportista(); //Generamos un zombie.

                                z.setPosFila(columnas - 1); //Fila del Zombie.
                                z.setPosColumna(filaZombie); //Columna del Zombie.

                                Casilla c = new Casilla(z); //Nueva Casilla que contiene Zombie.
                                tablero[filaZombie][columnas - 1] = c; //Asignamos al tablero la casilla creada.
                                ZombiesSalidos += 1; //Aumentamos en 1 los zombies que han salido.
                            } else {
                                TurnoSpawn[i] += 1; //El turno de spawn aumenta en 1.
                            }
                        }
                        break;
                }
                break;
            }
        } while (ZombiesSalidos >= nzombies); //Mientras que los zombies salidos sean mayores o iguales al número de zombies totales, se seguirá ejecutando el bucle.
    }

    /**
     * Genera un número aleatorio según un rango que se le proporciona.
     *
     * @param min, número mínimo del rango para generar el número random.
     * @param max, número máximo del rango para generar el número random.
     * @return, devuelve un número aleatorio.
     */
    private static int getRandomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("El maximo ha de ser mas grande que el minimo");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Genera la ayuda correspondiente al comnando help introducido por el
     * usuario.
     */
    private static void Ayuda() {
        System.out.println("N <filas> <columnas> <Dificultad>: Nueva partida (Dificultad: Facil, Normal, Dificil, Imposible).");
        System.out.println("G <fila> <columna>: colocar girasol. Únicamente se podrá añadir un nuevo Girasol por turno y \n "
                + "  si tiene el número suficiente de soles. No podrá añadir un Girasol en una casilla ocupada por otra planta o por un zombi.");
        System.out.println("L <fila> <columna>: colocar LanzaGuisantes. Únicamente se podrá añadir un nuevo LanzaGuisantes \n"
                + "  por turno y si tiene el número suficiente de soles. No podrá añadir un LanzaGuisantes en una casilla ocupada por otra planta \n "
                + "  o por un zombi.");
        System.out.println("S: Salir de la aplicación.");
        System.out.println("<Enter>: Pasar Turno");
        System.out.println("ayuda: este comando solicita a la aplicación que muestre la ayuda sobre cómo utilizar los comandos");
    }

    /**
     * Según los datos introducidos por el usuario se configura la partida y se
     * inicializa el tablero y los zombies correspondientes a la dificultad.
     */
    private static void InicioPartida() {

        if (comands.length == 4) { //Se comprueba la longitud del comando introducido.
            try {
                IniciarVariables(); //Asigna los valores correspondientes al tablero y asigna casillas de vacío.
                IniciarDificultad(); //Según la dificultad introducida, le asignamos el número correspondiente entre 1 y 4.
                IniciarTablero(); //Ésta función es la que inicia el tablero.
                IniciarZombies(); //Iniciamos los zombies segun la dificultad.
                Renderizar(tablero); //Ésta función imprime el tablero.
            } catch (NumberFormatException NFE) {
                System.out.println("Caracteres Invalidos");
            } catch (ExcepcionJuego ex) {
                System.out.println(ex.toString());
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Aqui Falla");
            }
        } else {
            System.out.println("ERROR: Comando invalido");
        }
    }

    /**
     * Utilizamos esta función para colocar un lanzaguisantes en el tablero.
     *
     * @throws ExcepcionJuego
     */
    private static void PlantarLanzaguisantes() throws ExcepcionJuego {
        if (StartGame) { //Comprobamos si el juego está iniciado.
            if (comands.length == 3) { //Comprobamos la longitud del comando.

                try {
                    int fila = Integer.parseInt(comands[1]); //Fila introducida por el usuario.
                    int columna = Integer.parseInt(comands[2]); //Columna introducida por el usuario.
                    if (tablero[fila - 1][columna - 1].isOcupado()) { //Si la casilla está ocupada, lanzamos la ExcepcionPlanta.                        
                        throw new ExcepcionPlanta("La casilla se encuentra ocupada");
                    } else if (lanzaColocado) { //Si ya hemos colocado este turno, lanzamos la ExcepcionPlanta.                        
                        throw new ExcepcionPlanta("Ya has colocado un lanzaguisantes este turno");
                    } else if (soles < 50) { //Si el coste excede los soles totales, lanzamos la EcepcionJuego.                        
                        throw new ExcepcionJuego("No tienes suficientes soles");
                    } else if (columna == columnas) { //Si se intenta colocar una planta en la última columna, lanzamos la ExcepcionJuego.
                        throw new ExcepcionJuego("No puedes plantar en la ultima casilla");
                    } else { //Si no, colocamos un lanzaguisantes en las coordenadas introducidas por el usuario.                        
                        lanzaColocado = true;
                        Lanzaguisantes l = new Lanzaguisantes();
                        l.setPosFila(fila);
                        l.setPosColumna(columna);
                        Casilla c = new Casilla(l);
                        tablero[fila - 1][columna - 1] = c;
                        soles -= 50; //Restamos su coste correspondiente a los soles totales.
                        Renderizar(tablero); //Actualizamos el tablero.
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(ex.toString());
                    System.out.println("Caracteres Invalidos");
                } catch (ExcepcionPlanta ex) {
                    System.out.println(ex.toString());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("SystemMessage > Las coordenadas han de estar dentro de los limites de tablero");
                }
            } else { //Si no coincide la longitud del comando.
                throw new ExcepcionJuego("Comando invalidos"); //Lanzamos la ExcepcionJuego.
            }
        } else { //Si no hay partida iniciada.
            throw new ExcepcionJuego("Inicia Partida Primero"); //Lanzamos la ExcepcionJuego.
        }
    }

    /**
     * Utilizamos esta función para colocar un nuez en el tablero.
     *
     * @throws ExcepcionJuego
     */
    private static void PlantarNuez() throws ExcepcionJuego {
        if (StartGame) { //Comprobamos si el juego está iniciado.
            if (comands.length == 3) { //Comprobamos la longitud del comando.

                try {
                    int fila = Integer.parseInt(comands[1]); //Fila introducida por el usuario.
                    int columna = Integer.parseInt(comands[2]); //Columna introducida por el usuario.
                    if (tablero[fila - 1][columna - 1].isOcupado()) { //Si la casilla está ocupada, lanzamos la ExcepcionPlanta.                        
                        throw new ExcepcionPlanta("La casilla se encuentra ocupada");
                    } else if (nuezColocado) { //Si ya hemos colocado este turno, lanzamos la ExcepcionPlanta.                        
                        throw new ExcepcionPlanta("Ya has colocado una nuez este turno");
                    } else if (soles < 50) { //Si el coste excede los soles totales, lanzamos la EcepcionJuego.                        
                        throw new ExcepcionJuego("No tienes suficientes soles");
                    } else if (columna == columnas) { //Si se intenta colocar una planta en la última columna, lanzamos la ExcepcionJuego.
                        throw new ExcepcionJuego("No puedes plantar en la ultima casilla");
                    } else { //Si no, colocamos un lanzaguisantes en las coordenadas introducidas por el usuario.                        
                        nuezColocado = true;
                        Nuez n = new Nuez();
                        n.setPosFila(fila);
                        n.setPosColumna(columna);
                        Casilla c = new Casilla(n);
                        tablero[fila - 1][columna - 1] = c;
                        soles -= 50; //Restamos su coste correspondiente a los soles totales.
                        Renderizar(tablero); //Actualizamos el tablero.
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(ex.toString());
                    System.out.println("Caracteres Invalidos");
                } catch (ExcepcionPlanta ex) {
                    System.out.println(ex.toString());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("SystemMessage > Las coordenadas han de estar dentro de los limites de tablero");
                }
            } else { //Si no coincide la longitud del comando.
                throw new ExcepcionJuego("Comando invalidos"); //Lanzamos la ExcepcionJuego.
            }
        } else { //Si no hay partida iniciada.
            throw new ExcepcionJuego("Inicia Partida Primero"); //Lanzamos la ExcepcionJuego.
        }
    }

    /**
     * Utilizamos esta función para colocar un petacereza en el tablero.
     *
     * @throws ExcepcionJuego
     */
    private static void PlantarPetacereza() throws ExcepcionJuego {
        if (StartGame) { //Comprobamos si el juego está iniciado.
            if (comands.length == 3) { //Comprobamos la longitud del comando.

                try {
                    int fila = Integer.parseInt(comands[1]); //Fila introducida por el usuario.
                    int columna = Integer.parseInt(comands[2]); //Columna introducida por el usuario.
                    if (tablero[fila - 1][columna - 1].isOcupado()) { //Si la casilla está ocupada, lanzamos la ExcepcionPlanta.                        
                        throw new ExcepcionPlanta("La casilla se encuentra ocupada");
                    } else if (petaColocado) { //Si ya hemos colocado este turno, lanzamos la ExcepcionPlanta.                        
                        throw new ExcepcionPlanta("Ya has colocado una petacereza este turno");
                    } else if (soles < 50) { //Si el coste excede los soles totales, lanzamos la EcepcionJuego.                        
                        throw new ExcepcionJuego("No tienes suficientes soles");
                    } else if (columna == columnas) { //Si se intenta colocar una planta en la última columna, lanzamos la ExcepcionJuego.
                        throw new ExcepcionJuego("No puedes plantar en la ultima casilla");
                    } else { //Si no, colocamos un lanzaguisantes en las coordenadas introducidas por el usuario.                        
                        nuezColocado = true;
                        PetaCereza p = new PetaCereza();
                        p.setPosFila(fila-1);
                        p.setPosColumna(columna-1);
                        Casilla c = new Casilla(p);
                        tablero[fila - 1][columna - 1] = c;
                        soles -= 50; //Restamos su coste correspondiente a los soles totales.
                        Renderizar(tablero); //Actualizamos el tablero.
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(ex.toString());
                    System.out.println("Caracteres Invalidos");
                } catch (ExcepcionPlanta ex) {
                    System.out.println(ex.toString());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("SystemMessage > Las coordenadas han de estar dentro de los limites de tablero");
                }
            } else { //Si no coincide la longitud del comando.
                throw new ExcepcionJuego("Comando invalidos"); //Lanzamos la ExcepcionJuego.
            }
        } else { //Si no hay partida iniciada.
            throw new ExcepcionJuego("Inicia Partida Primero"); //Lanzamos la ExcepcionJuego.
        }
    }

    /**
     * Utilizamos esta función para colocar un girasol en el tablero.
     *
     * @throws ExcepcionJuego
     */
    private static void PlantarGirasol() throws ExcepcionJuego, ExcepcionPlanta {
        if (StartGame) { //Comprobamos si el juego está iniciado.
            if (comands.length == 3) { //Comprobamos la longitud del comando.
                try {
                    int fila = Integer.parseInt(comands[1]); //Fila introducida por el usuario.
                    int columna = Integer.parseInt(comands[2]); //Columna introducida por el usuario.
                    if (tablero[fila - 1][columna - 1].isOcupado()) { //Si la casilla está ocupada, lanzamos la ExcepcionPlanta.                          
                        throw new ExcepcionPlanta("La casilla ya se encuentra ocupada");
                    } else if (girasolColocado) { //Si ya hemos colocado este turno, lanzamos la ExcepcionPlanta.                        
                        throw new ExcepcionPlanta("Ya has colocado un girasol este turno");
                    } else if (soles < 20) { //Si el coste excede los soles totales, lanzamos la ExcepcionPlanta.
                        throw new ExcepcionPlanta("No hay suficientes soles");
                    } else if (columna == columnas) { //Si se intenta colocar una planta en la última columna, lanzamos la ExcepcionJuego.
                        throw new ExcepcionPlanta("No puedes plantar en la ultima casilla");
                    } else { //Si no, colocamos un girasol en las coordenadas introducidas por el usuario.                        
                        girasolColocado = true;
                        Girasol g = new Girasol();
                        g.setPosColumna(columna);
                        g.setPosFila(fila);
                        Casilla c = new Casilla(g);
                        tablero[g.getPosFila() - 1][g.getPosColumna() - 1] = c;
                        soles -= 20; //Restamos su coste correspondiente a los soles totales.
                        Renderizar(tablero); //Actualizamos el tablero.
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Caracteres Invalidos");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("System Message > Las coordenadas han de estar dentro de los limites de tablero");
                }
            } else { //Si no coincide la longitud del comando.
                throw new ExcepcionJuego("Comando invalidos"); //Lanzamos la ExcepcionJuego.
            }
        } else { //Si no hay partida iniciada.
            throw new ExcepcionJuego("Inicia Partida Primero"); //Lanzamos la ExcepcionJuego.
        }
    }

    /**
     * Comprueba la situaciñon de la partida cada vez que se pasa el turno.
     *
     * @throws ExcepcionJuego
     */
    private static void PasarTurno() throws ExcepcionJuego {
        if (StartGame) { //Si hay una partida iniciada.
            CheckWin(); //Comprueba si ha ganado alguien.
            girasolColocado = false; //Se cambia colocar girasol a false.
            lanzaColocado = false; //Se cambia colocar lanzaguisantes a false.
            nuezColocado = false;
            petaColocado = false;
            turno++; //Aumenta en 1 el turno.
            SpawnZombie(); //Spawn de zombies.
            ActualizarTablero(); //Actualiza la situación del tablero.
            Renderizar(tablero); //Imprime el tablero.
        } else { //Si no hay una partida iniciada.
            throw new ExcepcionJuego("Inicia primero partida"); //Lanzamos la ExcepcionJuego.
        }
    }

    /**
     * Comprueba si ha ganado alguien.
     *
     * @throws ExcepcionJuego
     */
    private static void CheckWin() throws ExcepcionJuego {
        if (StartGame) { //Si hay una partida iniciada.
            if (ZombiesSalidos >= nzombies && !Zombies()) { //Win condition de las plantas
                victoriaPlantas = true;
            }
        } else { //Si no hay una partida iniciada.
            throw new ExcepcionJuego("Inicia primero partida"); //Lanzamos la ExcepcionJuego.
        }
    }

    /**
     * Ésta función inicia el tablero.
     */
    private static void IniciarTablero() {
        tablero = new Casilla[filas][columnas]; //Se inicializa en un array de dos dimensiones el tablero según las filas y columnas introducidas por el usuario.
        Vacio v = new Vacio(); //Inicizaliza vacío.
        Casilla c = new Casilla(v); //Inicializa las casilllas y las rellena de vacío.
        for (int i = 0; i < filas; i++) { //Recorre las fila del array.
            for (int j = 0; j < columnas; j++) { //Recorre las columnas del array.
                tablero[i][j] = c; //Se asignan las casillas al tablero.
            }
        }
    }

    /**
     * Inicializa varibales
     *
     * @throws ExcepcionJuego
     */
    private static void IniciarVariables() throws ExcepcionJuego {
        victoriaPlantas = false; //Inicializamos las variables de victoria de las plantas.
        victoriaZombies = false; //Inicializamos las variables de victoria de los zombies.
        ZombiesSalidos = 0; //Inicializamos los zombies salidos a 0.
        soles = 50; //Soles con los que comenzamos.
        turno = 0; //Turno de comienzo.
        filas = Integer.parseInt(comands[1]); //Filas del tablero.
        columnas = Integer.parseInt(comands[2]); //Columnas del tablero.
        if (filas > 0 && columnas > 0) { //Si las filas y las columnas son mayores que 0.
            StartGame = true; //Se inicia la partida.
        } else { //Si no, se lanza la ExcepcionJuego.
            throw new ExcepcionJuego("Las filas o columnas no pueden ser negativas");
        }
    }

    /**
     * Inicializamos la dificultad de la partida.
     *
     * @throws ExcepcionJuego
     */
    private static void IniciarDificultad() throws ExcepcionJuego {
        switch (comands[3].toLowerCase()) { //El comando introducido se convierte a minúsculas y se le adjudica un número.
            case "facil":
                dificultad = 1;
                break;
            case "normal":
                dificultad = 2;
                break;
            case "dificil":
                dificultad = 3;
                break;
            case "imposible":
                dificultad = 4;
                break;
            default:
                throw new ExcepcionJuego("Dificultad no valida"); //Se lanza un error si se introduce mal.
        }
    }

    /**
     * Actualiza la situación del tablero.
     *
     * @throws ExcepcionJuego
     */
    private static void ActualizarTablero() throws ExcepcionJuego {
        //Iniciamos las variables locales
        Entidad e;
        int GirasolesEnPartida = 0;
        int LanzaEnPartida = 0;
        int NuezEnPartida = 0;
        int PetaEnPartida = 0;
        ZombiesEnPartida = 0;
        int GirasolesCheked = 0;
        int LanzaCheked = 0;
        int NuezCheked = 0;
        int PetaCheked = 0;
        int ZombiesCheked = 0;
        //Conteo
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                e = tablero[i][j].getContenido();
                if (e.resistencia <= 0) {
                    Vacio v = new Vacio();
                    Casilla c = new Casilla(v);
                    tablero[i][j] = c;
                }
                if (e instanceof Girasol) {
                    GirasolesEnPartida++;
                } else if (e instanceof Lanzaguisantes) {
                    LanzaEnPartida++;
                } else if (e instanceof Nuez) {
                    NuezEnPartida++;
                } else if (e instanceof PetaCereza) {
                    PetaEnPartida++;
                } else if (e instanceof Zombie) {
                    ZombiesEnPartida++;
                }

            }
        }
//Accion

        //Girasol
        do {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    e = tablero[i][j].getContenido();
                    if (e instanceof Girasol) {
                        e.Accion();
                        GirasolesCheked++;
                    }
                }
            }
        } while (GirasolesCheked < GirasolesEnPartida);
        //Lanza
        do {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    e = tablero[i][j].getContenido();
                    if (e instanceof Lanzaguisantes) {
                        e.Accion();
                        LanzaCheked++;
                    }
                }
            }
        } while (LanzaCheked < LanzaEnPartida);
        //Nuez
        do {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    e = tablero[i][j].getContenido();
                    if (e instanceof Nuez) {
                        e.Accion();
                        NuezCheked++;
                    }
                }
            }
        } while (NuezCheked < NuezEnPartida);
        //Peta
        do {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    e = tablero[i][j].getContenido();
                    if (e instanceof PetaCereza) {
                        e.Accion();
                        PetaCheked++;
                    }
                }
            }
        } while (PetaCheked < PetaEnPartida);
        //Zombies
        do {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    e = tablero[i][j].getContenido();
                    if (e instanceof Zombie) {
                        e.Accion();
                        ZombiesCheked++;
                    }
                }
            }
        } while (ZombiesCheked < ZombiesEnPartida);
        //Limpieza
        for (int i = 0;
                i < filas;
                i++) {
            for (int j = 0; j < columnas; j++) {
                e = tablero[i][j].getContenido();
                if (e.resistencia <= 0) {
                    Vacio v = new Vacio();
                    Casilla c = new Casilla(v);
                    tablero[i][j] = c;
                }
            }
        }
    }

    /**
     * Indica si hay zombies en el tablero.
     *
     * @return ZombiesTablero
     */
    private static boolean Zombies() {
        Entidad e; //Inicializamos la entidad.
        ZombiesTablero = true; //Inicializamos la variable de los zombies del tablero a true.
        for (int i = 0; i < filas; i++) { //Recorre las filas del tablero.
            for (int j = 0; j < columnas; j++) { //Recorre las columnas del tablero.
                e = tablero[i][j].getContenido(); //Guardamos el contenido de la casilla en la variable de la entidad.
                ZombiesTablero = e instanceof Zombie; //Vemos si la entidad es un zombie.
                if (ZombiesTablero) { //Si es un zombie.
                    break; //Salimos del bucle.
                } else { //Si no es un zombie.
                    ZombiesTablero = false; //Se cambia la variable de los zombies del tablero a false.
                }
            }
        }
        return ZombiesTablero;
    }
}
