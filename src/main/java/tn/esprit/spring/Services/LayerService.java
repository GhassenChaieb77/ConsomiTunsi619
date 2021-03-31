package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Layer;
import tn.esprit.spring.Entities.Product;

public interface LayerService {

	
public Layer saveLayer(Layer l);
	
	
	public void DeleteLayer (long id);
	
	public void Update(Layer l);
	
	 public List<Layer> getAllLayer();

	
	
	public List<Product> GetP(long id);


	Layer getLayerById(long id);

	
}
