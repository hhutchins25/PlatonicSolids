package com.example.platonicsolids.geo;

import java.lang.Math;

public class Dodecahedron extends PlatonicSolid {

    private float u = (float)Math.pow(1.0/3.0, 0.5);
    private float nu = -1.0f * u;
    private float g = ((1.0f + (float)Math.sqrt(5)) / 2.0f) * u;
    private float ng = -1.0f * g;
    private float v = (float)(1.0f / ((1.0f + Math.sqrt(5)) / 2.0f)) * u;
    private float nv = -1.0f * v;

    private float verts[][] = {
            // inner cube
            {u,u,u}, {u,u,nu}, {u,nu,u}, {u,nu,nu},
            {nu,u,u}, {nu,u,nu}, {nu,nu,u}, {nu,nu,nu},
            // yz-plane
            {0,g,v}, {0,g,nv}, {0,ng,v}, {0,ng,nv},
            // xz-plane
            {v,0,g}, {v,0,ng}, {nv,0,g}, {nv,0,ng},
            // xy-plane
            {g,v,0}, {g,nv,0}, {ng,v,0}, {ng,nv,0}
    };

    private NGon pents[] = {
            new NGon(new float[][] {verts[0],verts[16],verts[1],verts[9], verts[8]}),
            new NGon(new float[][] {verts[9],verts[1],verts[13],verts[15],verts[5]}),
            new NGon(new float[][] {verts[0],verts[12],verts[2],verts[17],verts[16]}),
            new NGon(new float[][] {verts[17],verts[2],verts[10],verts[11],verts[3]}),
            new NGon(new float[][] {verts[0],verts[8],verts[4],verts[14],verts[12]}),
            new NGon(new float[][] {verts[14],verts[4],verts[18],verts[19],verts[6]}),

            new NGon(new float[][] {verts[7],verts[11],verts[10],verts[6],verts[19]}),
            new NGon(new float[][] {verts[6],verts[10],verts[2],verts[12],verts[14]}),
            new NGon(new float[][] {verts[7],verts[15],verts[13],verts[3],verts[11]}),
            new NGon(new float[][] {verts[3],verts[13],verts[1],verts[16],verts[17]}),
            new NGon(new float[][] {verts[7],verts[19],verts[18],verts[5],verts[15]}),
            new NGon(new float[][] {verts[5],verts[18],verts[4],verts[8],verts[9]})

    };

    public Dodecahedron() {

    }

    public void draw(float[] mvpMatrix) {
        for(int i=0; i<12; i++) {
            pents[i].draw(mvpMatrix);
        }
    }
}
