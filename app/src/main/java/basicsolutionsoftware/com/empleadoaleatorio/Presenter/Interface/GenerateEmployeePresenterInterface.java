package basicsolutionsoftware.com.empleadoaleatorio.Presenter.Interface;

public interface GenerateEmployeePresenterInterface {

    String getNssAleatory();

    void getNombreAleatorio(int sexo);

    void getSegundoNombreAleatorio(int sexo);

    void getApellidoPaternoAleatorio();

    void getApellidoMaternoAleatorio();

    void getEdadAleatorio();

    void getSexoAleatorio();

    void generateCURPAleatorio(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String sexo, String codigoEntidad);

    void getEntidadAleatorio();

}
