package Labs.Lab_7;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.*;


public class Crawler {

    private static class URLDepthPair { // класс для наших пар [URL, Depth]
        private String url;
        private int depth;

        public URLDepthPair(String url, int depth) {
            this.url = url;
            this.depth = depth;
        }

        public String getUrl() {
            return url;
        }

        public int getDepth() {
            return depth;
        }

        @Override
        public String toString() {
            return "[ " + url + ", " + depth + " ]";
        }

        public String getHost() throws MalformedURLException { // получаем хост, т.е. ссылку без протокола
            URL host = new URL(url);
            return host.getHost();
        }
    }

    public static final String ERROR_MESSAGE = "usage: java Crawler <URL><depth>";
    public static final int DEFAULT_PORT = 80;

    public static void main(String[] args) throws MalformedURLException {
        if (args.length != 2)
            System.out.println(ERROR_MESSAGE);

        String url = args[0];
        int maxDepth = 0;

        try {
            URL checkUrl = new URL(url); // создаем объект для чека на правильность ссылки
            maxDepth = Integer.parseInt(args[1]);
        } catch (NumberFormatException | MalformedURLException e) {
            System.out.println(ERROR_MESSAGE);
        }


        // Связанный список - список, в котором каждый узел хранит значение и ссылку на следующий элемент.
        // Преимущества - он динамический в отличии от массива.
        // Недостатки - медленный (чтобы удалить или вставить элемент в середину, нужно перебирать от начала), больше
        // памяти требуется (так как храним значение и ссылку), также доступ по индексу
        LinkedList<URLDepthPair> pairs = new LinkedList<URLDepthPair>();
        LinkedList<URLDepthPair> visited = new LinkedList<>();
        pairs.add(new URLDepthPair(url, 0));

        while(!pairs.isEmpty()) { // пока наш список непустой делаем следующее:
            URLDepthPair pair = pairs.poll(); // pol() - удаляет 1 - ый элемент и присваивает этот элемент pair
            int nextDepth = pair.getDepth(); // получаем глубину
            String nextHost = pair.getHost(); // получаем хост
            if (pair.getDepth() != 0) // если это 1 пара, то не добавляем в список посещенных так, как глубина это
                // ссылка на странице а не вводная ссылка
                visited.add(pair);

            if (nextDepth < maxDepth) {
                try {
                    Socket socket = new Socket(nextHost, DEFAULT_PORT); // создаем экземпляр сокета
                    /*
                        Printer Writer - это класс на языке программирования Java,
                    который используется для записи символьных данных в поток.
                    Это подкласс Writer и используется для записи символьных данных в файл или другой выходной поток.
                    OutputStreamWriter - то же самое, только ещё можно записывать в выходной поток сетевого подключения.
                     */

                    PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),
                            true); //

                    // Отправляем запрос на сервер через сокет
                    output.println("GET / HTTP/1.1");
                    output.println("Host:" + nextHost);
                    output.println("Connection: close");
                    output.println();

                    /*
                        BufferedReader - это класс на языке программирования Java,
                    который используется для чтения символьных данных из потока.
                    Это подкласс Reader и используется для считывания данных из файла или
                    другого входного потока путем буферизации данных в памяти.
                    InputStreamReader - то же самое, только можно еще из сетевого потока.
                     */
                    // Читаем строки данных полученных от сервера
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder(); // ответ от сервера
                    while ((line = input.readLine()) != null) { // input.readline() - метод, который считывает строку
                        // текста из входного потока и сохраняет её в переменной line, до тех пор пока метод не вернет null
                        response.append(line).append("\n"); // добавляем в ответ от сервера строку текста из входного потока
                    }


                    /*
                    Этот код использует библиотеку JSoup для анализа HTML-ответа с сервера и
                    извлечения всех ссылок в документе.
                     */

                    Document doc = Jsoup.parse(response.toString()); // в экземляре хранится проанализированный HTML-документ
                    // метод parse - метод, который вызывается для анализа ответа HTML


                    // doc.select - метод "выборка" всех элементов в doc, которые соответсвуют указанному селектору
                    // a[href] - Селектор. Выбирает все элементы <a> имеющие атрибут href. Метод возвращает Elements
                    // Elements - коллекция объектов Element
                    Elements links = doc.select("a[href]");
                    for (Element link : links) {
                        String href = link.attr("href");
                        // c помощью метода .attr "извлекаем" значение атрибута href (ссылка)
                        if (href.startsWith("http://")) { // если ссылка начинается с протокола http://
                            pairs.add(new URLDepthPair(href, nextDepth + 1)); // то добавляем в наш список
                            // с глубиной + 1
                            break; // брейкуем
                        }
                    }
                    // закрываем сокет
                    socket.close();
                } catch (IOException e) { // обрабатываем исключение
                    e.printStackTrace();
                }
            }
        }
        System.out.println(visited); // выводим наши пары посещенных url с их глубинами
    }
}
