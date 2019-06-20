package net.netne.cmessiah.codemessiah;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class hotlines_class extends Fragment {

    TextView cc,emergency,pagasa,phivolcs_1,phivolcs_2,ndrrmc_1,ndrrmc_2,ndrrmc_3,ndrrmc_4,pcg_1,pnp_1,bfp_1,bfp_2,bfp_3,bfp_4,dswd_1,mmda,prc,dpwh,mw,meralco, tvphoneno;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.hotlines_layout,null);

        pagasa = (TextView) view.findViewById(R.id.pagasa1);
        phivolcs_1= (TextView) view.findViewById(R.id.phivolcs1);
        phivolcs_2= (TextView) view.findViewById(R.id.phivolcs2);
        ndrrmc_1= (TextView) view.findViewById(R.id.ndrrmc1);
        ndrrmc_2= (TextView) view.findViewById(R.id.ndrrmc2);
        ndrrmc_3= (TextView) view.findViewById(R.id.ndrrmc3);
        ndrrmc_4= (TextView) view.findViewById(R.id.ndrrmc4);
        pcg_1= (TextView) view.findViewById(R.id.pcg1);
        pnp_1= (TextView) view.findViewById(R.id.pnp1);
        bfp_1= (TextView) view.findViewById(R.id.bfp1);
        bfp_2= (TextView) view.findViewById(R.id.bfp2);
        bfp_3= (TextView) view.findViewById(R.id.bfp3);
        bfp_4= (TextView) view.findViewById(R.id.bfp4);
        dswd_1= (TextView) view.findViewById(R.id.dswd1);
        mmda= (TextView) view.findViewById(R.id.mmda1);
        prc= (TextView) view.findViewById(R.id.prc1);
        dpwh= (TextView) view.findViewById(R.id.dpwh1);
        mw= (TextView) view.findViewById(R.id.mw1);
        meralco= (TextView) view.findViewById(R.id.meralco);
        cc = (TextView) view.findViewById(R.id.cc);
        emergency = (TextView) view.findViewById(R.id.eh);

        emergency.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="911";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        cc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="8888";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });
        pagasa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="4942696";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        phivolcs_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="4261468";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        phivolcs_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="9274524";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        ndrrmc_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="9111406";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        ndrrmc_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="9122665";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        ndrrmc_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="9125668";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        ndrrmc_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="9120441";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        pcg_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="5278481";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        pnp_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="7230401";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        bfp_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="7295166";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        bfp_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="4106254";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        bfp_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="4318859";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        bfp_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="4071230";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        dswd_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="9318068";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        mmda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="136";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        prc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="7902300";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        dpwh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="3043000";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        mw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="1627";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });

        meralco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert= null;
                AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  number="16211";
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                    }
                });
                build.show();
            }
        });



        return view;
    }


}
